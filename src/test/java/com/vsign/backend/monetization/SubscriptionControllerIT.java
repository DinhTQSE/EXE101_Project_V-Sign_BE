package com.vsign.backend.monetization;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SubscriptionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listsSubscriptionPlansWithConcreteDtoFields() throws Exception {
        mockMvc.perform(get("/api/v1/subscriptions/plans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(3))
                .andExpect(jsonPath("$.data[0].planId").value("free"));
    }

    @Test
    void listsActivePlansFromPlannedSingularEndpoint() throws Exception {
        mockMvc.perform(get("/api/v1/subscription/plans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.plans.length()").value(3))
                .andExpect(jsonPath("$.data.plans[0].planId").value("pro-monthly"))
                .andExpect(jsonPath("$.data.plans[0].active").value(true));
    }

    @Test
    void createsCheckoutIntentForPaidPlan() throws Exception {
        mockMvc.perform(post("/api/v1/subscriptions/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "planId": "pro-monthly",
                                  "userId": "user-1001",
                                  "successUrl": "https://vsign.test/payment/success",
                                  "cancelUrl": "https://vsign.test/payment/cancel"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.planId").value("pro-monthly"));
    }

    @Test
    void createsPaymentOrderForMomoPlan() throws Exception {
        mockMvc.perform(post("/api/v1/payments/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "provider": "MOMO",
                                  "planId": "pro-monthly",
                                  "amount": 99000
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.transactionId").isNotEmpty())
                .andExpect(jsonPath("$.data.provider").value("MOMO"))
                .andExpect(jsonPath("$.data.status").value("PENDING"));
    }

    @Test
    void rejectsInvalidPaymentProviderAmountAndPlan() throws Exception {
        mockMvc.perform(post("/api/v1/payments/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "provider": "CARD",
                                  "planId": "pro-monthly",
                                  "amount": 99000
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));

        mockMvc.perform(post("/api/v1/payments/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "provider": "MOMO",
                                  "planId": "pro-monthly",
                                  "amount": 1000
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));

        mockMvc.perform(post("/api/v1/payments/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "provider": "ZALOPAY",
                                  "planId": "retired-plan",
                                  "amount": 99000
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    @Test
    void returnsPaymentStatusAndMissingTransactionError() throws Exception {
        String response = mockMvc.perform(post("/api/v1/payments/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "provider": "MOMO",
                                  "planId": "pro-monthly",
                                  "amount": 99000
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String txId = com.fasterxml.jackson.databind.json.JsonMapper.builder().build()
                .readTree(response).path("data").path("transactionId").asText();

        mockMvc.perform(get("/api/v1/payments/{transactionId}", txId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.transactionId").value(txId))
                .andExpect(jsonPath("$.data.status").isNotEmpty())
                .andExpect(jsonPath("$.data.retryable").isBoolean());

        mockMvc.perform(get("/api/v1/payments/missing-transaction"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }
}
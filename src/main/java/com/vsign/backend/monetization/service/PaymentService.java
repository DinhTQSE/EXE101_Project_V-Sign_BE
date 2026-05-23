package com.vsign.backend.monetization.service;

import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import com.vsign.backend.monetization.dto.CreatePaymentOrderRequest;
import com.vsign.backend.monetization.dto.PaymentOrderResponse;
import com.vsign.backend.monetization.dto.PaymentStatusResponse;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Map<String, BigDecimal> PLAN_PRICE = Map.of(
            "pro-monthly", BigDecimal.valueOf(99000),
            "school-monthly", BigDecimal.valueOf(499000)
    );

    private final Map<String, PaymentStatusResponse> statuses = new ConcurrentHashMap<>();

    public PaymentOrderResponse createOrder(CreatePaymentOrderRequest request) {
        if (request == null || isBlank(request.provider()) || isBlank(request.planId()) || request.amount() == null) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "provider, planId and amount are required");
        }

        if (!"MOMO".equals(request.provider()) && !"ZALOPAY".equals(request.provider())) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Unsupported payment provider");
        }

        BigDecimal expectedAmount = PLAN_PRICE.get(request.planId());
        if (expectedAmount == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND, "Subscription plan not found");
        }

        if (request.amount().compareTo(expectedAmount) != 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Amount does not match plan price");
        }

        String transactionId = "tx_" + UUID.randomUUID().toString().replace("-", "");
        PaymentStatusResponse status = new PaymentStatusResponse(
                transactionId,
                "PENDING",
                "AWAITING_PAYMENT",
                true,
                "Waiting for payment confirmation"
        );
        statuses.put(transactionId, status);

        return new PaymentOrderResponse(
                transactionId,
                request.provider(),
                "MOCKQR:" + transactionId,
                "https://payments.v-sign.test/redirect/" + transactionId,
                Instant.now().plusSeconds(900),
                status.status()
        );
    }

    public PaymentStatusResponse getStatus(String transactionId) {
        PaymentStatusResponse status = statuses.get(transactionId);
        if (status == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND, "Transaction not found");
        }
        return status;
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}

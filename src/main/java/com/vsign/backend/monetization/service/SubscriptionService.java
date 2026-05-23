package com.vsign.backend.monetization.service;

import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import com.vsign.backend.monetization.dto.ActivePlanResponse;
import com.vsign.backend.monetization.dto.CheckoutIntentRequest;
import com.vsign.backend.monetization.dto.CheckoutIntentResponse;
import com.vsign.backend.monetization.dto.PlanListResponse;
import com.vsign.backend.monetization.dto.SubscriptionPlanResponse;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private static final List<SubscriptionPlanResponse> PLANS = List.of(
            new SubscriptionPlanResponse(
                    "free",
                    "Free Learner",
                    BigDecimal.ZERO,
                    "VND",
                    3,
                    List.of("Dictionary access", "Basic signature practice", "Community learning path")
            ),
            new SubscriptionPlanResponse(
                    "pro-monthly",
                    "Pro Monthly",
                    BigDecimal.valueOf(99000),
                    "VND",
                    50,
                    List.of("Unlimited dictionary access", "Document upload workflow", "Advanced signature feedback")
            ),
            new SubscriptionPlanResponse(
                    "school-monthly",
                    "School Monthly",
                    BigDecimal.valueOf(499000),
                    "VND",
                    500,
                    List.of("Teacher dashboard", "Class progress reports", "Bulk learner management")
            )
    );

    public List<SubscriptionPlanResponse> listPlans() {
        return PLANS;
    }

    public PlanListResponse listActivePlans() {
        return new PlanListResponse(List.of(
                toActivePlan(PLANS.get(1), true),
                toActivePlan(PLANS.get(2), true),
                toActivePlan(PLANS.get(0), true)
        ));
    }

    public CheckoutIntentResponse createCheckoutIntent(CheckoutIntentRequest request) {
        if (request == null || isBlank(request.planId()) || isBlank(request.userId())) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "planId and userId are required");
        }

        SubscriptionPlanResponse plan = PLANS.stream()
                .filter(candidate -> candidate.planId().equals(request.planId()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_REQUEST, "Unsupported subscription plan"));

        if (plan.monthlyPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Checkout is only available for paid plans");
        }

        String checkoutIntentId = "chk_" + request.userId() + "_" + request.planId();
        return new CheckoutIntentResponse(
                checkoutIntentId,
                plan.planId(),
                "MOCK_PAYMENT",
                "PENDING",
                plan.monthlyPrice(),
                plan.currency(),
                "https://payments.v-sign.test/checkout/" + checkoutIntentId
        );
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private static ActivePlanResponse toActivePlan(SubscriptionPlanResponse plan, boolean active) {
        return new ActivePlanResponse(
                plan.planId(),
                plan.displayName(),
                plan.monthlyPrice(),
                plan.currency(),
                plan.maxUploadsPerMonth(),
                plan.features(),
                active
        );
    }
}

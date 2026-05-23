package com.vsign.backend.monetization.dto;

import java.math.BigDecimal;
import java.util.List;

public record SubscriptionPlanResponse(
        String planId,
        String displayName,
        BigDecimal monthlyPrice,
        String currency,
        int maxUploadsPerMonth,
        List<String> features
) {
}

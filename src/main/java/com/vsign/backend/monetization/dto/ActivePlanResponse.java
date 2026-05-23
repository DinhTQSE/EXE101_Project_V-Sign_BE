package com.vsign.backend.monetization.dto;

import java.math.BigDecimal;
import java.util.List;

public record ActivePlanResponse(
        String planId,
        String displayName,
        BigDecimal amount,
        String currency,
        int maxUploadsPerMonth,
        List<String> features,
        boolean active
) {
}

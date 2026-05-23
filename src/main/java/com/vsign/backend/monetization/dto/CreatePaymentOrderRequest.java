package com.vsign.backend.monetization.dto;

import java.math.BigDecimal;

public record CreatePaymentOrderRequest(
        String provider,
        String planId,
        BigDecimal amount
) {
}

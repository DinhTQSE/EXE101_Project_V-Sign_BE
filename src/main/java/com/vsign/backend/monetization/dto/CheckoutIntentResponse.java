package com.vsign.backend.monetization.dto;

import java.math.BigDecimal;

public record CheckoutIntentResponse(
        String checkoutIntentId,
        String planId,
        String provider,
        String status,
        BigDecimal amount,
        String currency,
        String checkoutUrl
) {
}

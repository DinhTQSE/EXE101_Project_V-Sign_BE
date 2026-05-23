package com.vsign.backend.monetization.dto;

public record CheckoutIntentRequest(
        String planId,
        String userId,
        String successUrl,
        String cancelUrl
) {
}

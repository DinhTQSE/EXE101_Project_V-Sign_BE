package com.vsign.backend.monetization.dto;

import java.time.Instant;

public record PaymentOrderResponse(
        String transactionId,
        String provider,
        String qrPayload,
        String checkoutUrl,
        Instant expiresAt,
        String status
) {
}

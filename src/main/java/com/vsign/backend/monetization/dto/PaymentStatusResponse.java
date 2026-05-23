package com.vsign.backend.monetization.dto;

public record PaymentStatusResponse(
        String transactionId,
        String status,
        String reasonCode,
        boolean retryable,
        String userMessage
) {
}

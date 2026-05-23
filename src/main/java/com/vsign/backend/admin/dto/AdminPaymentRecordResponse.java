package com.vsign.backend.admin.dto;

public record AdminPaymentRecordResponse(
        String transactionId,
        String userId,
        String provider,
        String status,
        long amountVnd,
        String createdAt,
        String updatedAt
) {
}

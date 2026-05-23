package com.vsign.backend.admin.dto;

public record AdminKpiResponse(
        String fromDate,
        String toDate,
        int totalUsers,
        int activeSubscriptions,
        long totalRevenueVnd,
        int successfulTransactions
) {
}

package com.vsign.backend.auth.dto;

import java.time.Instant;
import java.util.List;

public record ProfileResponse(
        String email,
        String fullName,
        String role,
        String avatarUrl,
        String accountType,
        int totalXp,
        int currentStreak,
        int longestStreak,
        List<BadgeSummaryResponse> badges,
        SubscriptionSummaryResponse subscription
) {
    public static ProfileResponse fromUser(
            String email,
            String fullName,
            String role,
            String avatarUrl,
            String accountType,
            int totalXp,
            int currentStreak,
            int longestStreak
    ) {
        return new ProfileResponse(
                email,
                fullName,
                role,
                avatarUrl,
                accountType,
                totalXp,
                currentStreak,
                longestStreak,
                List.of(),
                SubscriptionSummaryResponse.placeholder(accountType)
        );
    }

    public record BadgeSummaryResponse(
            String code,
            String name,
            Instant unlockedAt
    ) {
    }

    public record SubscriptionSummaryResponse(
            String planType,
            String status,
            Instant startDate,
            Instant endDate
    ) {
        static SubscriptionSummaryResponse placeholder(String accountType) {
            return new SubscriptionSummaryResponse(accountType, "INACTIVE", null, null);
        }
    }
}

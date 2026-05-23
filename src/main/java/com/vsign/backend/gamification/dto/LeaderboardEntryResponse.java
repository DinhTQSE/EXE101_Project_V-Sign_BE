package com.vsign.backend.gamification.dto;

public record LeaderboardEntryResponse(
        int rank,
        String userId,
        String displayName,
        int score,
        int totalXp,
        int level,
        int currentStreakDays
) {
}

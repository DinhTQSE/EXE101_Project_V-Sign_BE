package com.vsign.backend.gamification.dto;

import java.util.List;

public record UserProgressSummaryResponse(
        String userId,
        String displayName,
        int level,
        int totalXp,
        int currentStreakDays,
        int longestStreakDays,
        int nextLevelXp,
        int completedLessons,
        int completedAssessments,
        List<BadgeResponse> badges
) {
}

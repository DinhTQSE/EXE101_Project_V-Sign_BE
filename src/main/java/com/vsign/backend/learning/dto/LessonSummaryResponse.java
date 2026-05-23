package com.vsign.backend.learning.dto;

public record LessonSummaryResponse(
        String lessonId,
        String title,
        int orderIndex,
        int durationSeconds,
        boolean requiresPremium,
        boolean locked,
        String status,
        int progressPct
) {
}

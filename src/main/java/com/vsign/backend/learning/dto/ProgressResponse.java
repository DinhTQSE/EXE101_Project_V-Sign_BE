package com.vsign.backend.learning.dto;

public record ProgressResponse(
        String lessonId,
        int completionPct,
        int lastPositionSeconds,
        String phase,
        int currentQuestionIndex,
        String status
) {
}

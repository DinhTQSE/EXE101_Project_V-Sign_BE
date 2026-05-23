package com.vsign.backend.learning.dto;

public record LessonProgressCheckpointResponse(
        int completionPct,
        int lastPositionSeconds,
        String phase,
        int currentQuestionIndex,
        String status
) {
}

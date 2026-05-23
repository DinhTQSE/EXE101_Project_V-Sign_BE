package com.vsign.backend.learning.dto;

public record UpdateProgressRequest(
        Integer completionPct,
        Integer lastPositionSeconds,
        String phase,
        Integer currentQuestionIndex,
        String status
) {
}

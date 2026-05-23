package com.vsign.backend.assessment.dto;

public record AssessmentSummaryResponse(
        String id,
        String title,
        String description,
        String difficulty,
        int questionCount,
        int passingScore,
        int estimatedMinutes
) {
}

package com.vsign.backend.assessment.dto;

import java.util.List;

public record AssessmentDetailResponse(
        String id,
        String title,
        String description,
        String difficulty,
        int passingScore,
        int estimatedMinutes,
        List<QuestionResponse> questions
) {
}

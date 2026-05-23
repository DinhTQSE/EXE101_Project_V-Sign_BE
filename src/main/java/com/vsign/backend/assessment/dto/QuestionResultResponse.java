package com.vsign.backend.assessment.dto;

public record QuestionResultResponse(
        String questionId,
        String selectedOptionId,
        String correctOptionId,
        boolean correct
) {
}

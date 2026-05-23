package com.vsign.backend.assessment.dto;

public record QuizReviewQuestionResponse(
        String questionId,
        String prompt,
        String selectedAnswerId,
        String correctAnswerId,
        boolean correct,
        String explanation
) {
}

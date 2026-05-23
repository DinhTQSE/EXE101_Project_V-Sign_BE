package com.vsign.backend.assessment.dto;

public record QuizAnswerRequest(
        String questionId,
        String selectedAnswerId
) {
}

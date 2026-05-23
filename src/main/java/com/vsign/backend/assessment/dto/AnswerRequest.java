package com.vsign.backend.assessment.dto;

public record AnswerRequest(
        String questionId,
        String selectedOptionId
) {
}

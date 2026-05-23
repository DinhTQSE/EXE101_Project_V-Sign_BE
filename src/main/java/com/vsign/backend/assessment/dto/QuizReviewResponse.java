package com.vsign.backend.assessment.dto;

import java.util.List;

public record QuizReviewResponse(
        String attemptId,
        String quizId,
        List<QuizReviewQuestionResponse> questions
) {
}

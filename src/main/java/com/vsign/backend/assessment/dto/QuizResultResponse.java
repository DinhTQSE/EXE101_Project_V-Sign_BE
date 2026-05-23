package com.vsign.backend.assessment.dto;

public record QuizResultResponse(
        String attemptId,
        String quizId,
        int score,
        boolean passed,
        int xpAwarded,
        boolean reviewAvailable,
        boolean timedOut,
        int unansweredCount
) {
}

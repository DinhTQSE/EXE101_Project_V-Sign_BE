package com.vsign.backend.assessment.dto;

import java.util.List;

public record QuizResponse(
        String quizId,
        String lessonId,
        String attemptId,
        String title,
        int passingScore,
        int timeLimitSeconds,
        List<QuestionResponse> questions
) {
}

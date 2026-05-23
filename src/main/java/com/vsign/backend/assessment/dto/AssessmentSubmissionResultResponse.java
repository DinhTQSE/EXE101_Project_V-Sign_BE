package com.vsign.backend.assessment.dto;

import java.util.List;

public record AssessmentSubmissionResultResponse(
        String assessmentId,
        String userId,
        int score,
        boolean passed,
        int correctAnswers,
        int totalQuestions,
        int awardedXp,
        List<QuestionResultResponse> questionResults
) {
}

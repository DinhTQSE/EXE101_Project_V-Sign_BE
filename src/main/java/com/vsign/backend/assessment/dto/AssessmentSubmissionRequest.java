package com.vsign.backend.assessment.dto;

import java.util.List;

public record AssessmentSubmissionRequest(
        String userId,
        List<AnswerRequest> answers
) {
}

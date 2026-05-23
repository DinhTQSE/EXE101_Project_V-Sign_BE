package com.vsign.backend.assessment.dto;

import java.util.List;

public record SubmitAttemptRequest(
        List<QuizAnswerRequest> answers,
        Integer durationSeconds
) {
}

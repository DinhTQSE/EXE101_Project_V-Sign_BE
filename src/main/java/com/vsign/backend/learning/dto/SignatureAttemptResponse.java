package com.vsign.backend.learning.dto;

import java.util.List;

public record SignatureAttemptResponse(
        String attemptId,
        String userStoryId,
        String practiceItemId,
        String documentUploadId,
        String status,
        int score,
        List<String> feedbackCodes
) {
}

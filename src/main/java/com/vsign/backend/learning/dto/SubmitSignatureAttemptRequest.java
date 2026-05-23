package com.vsign.backend.learning.dto;

public record SubmitSignatureAttemptRequest(
        String userStoryId,
        String practiceItemId,
        String documentUploadId,
        String signatureVector,
        Integer durationMs
) {
}

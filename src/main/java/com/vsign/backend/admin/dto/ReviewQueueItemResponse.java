package com.vsign.backend.admin.dto;

public record ReviewQueueItemResponse(
        String contentId,
        String contentType,
        String submittedBy,
        String title,
        String status,
        String priority,
        String submittedAt
) {
}

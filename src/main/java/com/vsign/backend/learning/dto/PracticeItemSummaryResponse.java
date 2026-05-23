package com.vsign.backend.learning.dto;

public record PracticeItemSummaryResponse(
        String itemId,
        String lessonId,
        String title,
        String category,
        String level,
        int estimatedSeconds
) {
}

package com.vsign.backend.learning.dto;

public record UnitSummaryResponse(
        String unitId,
        String title,
        String thumbnailUrl,
        int chapterCount,
        int orderIndex
) {
}

package com.vsign.backend.learning.dto;

public record ChapterSummaryResponse(
        String chapterId,
        String title,
        int orderIndex,
        int lessonCount,
        boolean requiresPremium,
        boolean locked,
        int progressPct
) {
}

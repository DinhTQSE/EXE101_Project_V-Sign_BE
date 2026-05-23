package com.vsign.backend.learning.dto;

public record LessonDetailResponse(
        String lessonId,
        String chapterId,
        String title,
        String videoUrl,
        boolean requiresPremium,
        boolean locked,
        LessonProgressCheckpointResponse progress
) {
}

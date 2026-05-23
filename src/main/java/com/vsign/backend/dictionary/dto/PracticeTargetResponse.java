package com.vsign.backend.dictionary.dto;

public record PracticeTargetResponse(
        String entryId,
        String unitId,
        String chapterId,
        String lessonId,
        String quizId,
        boolean requiresPremium
) {
}

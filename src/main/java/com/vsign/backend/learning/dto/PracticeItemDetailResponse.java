package com.vsign.backend.learning.dto;

import java.util.List;

public record PracticeItemDetailResponse(
        String itemId,
        String lessonId,
        String title,
        String category,
        String level,
        String expectedGloss,
        String prompt,
        List<String> rubric
) {
}

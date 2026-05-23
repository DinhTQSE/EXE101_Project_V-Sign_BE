package com.vsign.backend.learning.dto;

import java.util.List;

public record PracticeItemsPageResponse(
        List<PracticeItemSummaryResponse> content,
        int page,
        int size,
        int totalElements,
        int totalPages
) {
}

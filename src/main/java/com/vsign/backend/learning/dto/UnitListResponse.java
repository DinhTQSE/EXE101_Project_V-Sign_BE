package com.vsign.backend.learning.dto;

import java.util.List;

public record UnitListResponse(
        List<UnitSummaryResponse> units,
        int page,
        int size,
        int totalElements,
        int totalPages
) {
}

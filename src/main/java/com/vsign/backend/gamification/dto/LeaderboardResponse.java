package com.vsign.backend.gamification.dto;

import java.util.List;

public record LeaderboardResponse(
        String generatedFrom,
        LeaderboardPeriod period,
        int page,
        int size,
        long totalElements,
        int totalPages,
        List<LeaderboardEntryResponse> entries,
        LeaderboardEntryResponse currentUser
) {
}

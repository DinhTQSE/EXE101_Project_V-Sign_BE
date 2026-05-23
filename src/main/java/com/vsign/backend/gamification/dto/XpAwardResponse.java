package com.vsign.backend.gamification.dto;

public record XpAwardResponse(
        String eventId,
        boolean duplicate,
        UserProgressSummaryResponse summary
) {
}

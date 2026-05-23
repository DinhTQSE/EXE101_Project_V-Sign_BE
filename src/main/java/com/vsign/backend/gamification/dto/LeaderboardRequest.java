package com.vsign.backend.gamification.dto;

public record LeaderboardRequest(
        String period,
        Integer page,
        Integer size,
        String userId
) {
}

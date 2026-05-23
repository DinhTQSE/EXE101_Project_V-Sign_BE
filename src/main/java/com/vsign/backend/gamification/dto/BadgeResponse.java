package com.vsign.backend.gamification.dto;

import java.time.LocalDate;

public record BadgeResponse(
        String id,
        String name,
        String description,
        LocalDate earnedAt
) {
}

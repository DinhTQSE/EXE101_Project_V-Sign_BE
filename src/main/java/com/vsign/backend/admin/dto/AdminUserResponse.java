package com.vsign.backend.admin.dto;

public record AdminUserResponse(
        String userId,
        String email,
        String fullName,
        String role,
        String status,
        int currentStreak,
        int xp
) {
}

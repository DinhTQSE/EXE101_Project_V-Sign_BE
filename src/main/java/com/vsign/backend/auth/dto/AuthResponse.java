package com.vsign.backend.auth.dto;

public record AuthResponse(
        String accessToken,
        String tokenType,
        AuthUserResponse user
) {
    public static AuthResponse bearerToken(
            String accessToken,
            String email,
            String fullName,
            String role,
            String accountType
    ) {
        return new AuthResponse(accessToken, "Bearer", new AuthUserResponse(email, fullName, role, accountType));
    }

    public record AuthUserResponse(
            String email,
            String fullName,
            String role,
            String accountType
    ) {
    }
}

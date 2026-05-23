package com.vsign.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 8, max = 72)
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
                message = "password must contain at least one uppercase letter and one number"
        )
        String password,

        @NotBlank
        @Size(max = 120)
        String fullName
) {
}

package com.vsign.backend.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UpdateProfileRequest(
        @NotBlank
        @Size(max = 120)
        String fullName,

        @URL
        @Size(max = 500)
        String avatarUrl
) {
}

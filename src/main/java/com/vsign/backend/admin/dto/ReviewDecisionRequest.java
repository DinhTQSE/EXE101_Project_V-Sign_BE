package com.vsign.backend.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewDecisionRequest(
        @NotBlank String decision,
        @NotBlank String reason
) {
}

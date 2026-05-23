package com.vsign.backend.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record ManualPaymentStatusRequest(
        @NotBlank String status,
        @NotBlank String reason
) {
}

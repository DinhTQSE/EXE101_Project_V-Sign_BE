package com.vsign.backend.common.exception;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String code,
        String message,
        String path,
        List<ValidationError> validationErrors
) {
    public static ApiErrorResponse of(ErrorCode code, String message, String path) {
        return new ApiErrorResponse(
                Instant.now(),
                code.status().value(),
                code.status().getReasonPhrase(),
                code.name(),
                message,
                path,
                List.of()
        );
    }

    public record ValidationError(
            String field,
            String message
    ) {
    }
}

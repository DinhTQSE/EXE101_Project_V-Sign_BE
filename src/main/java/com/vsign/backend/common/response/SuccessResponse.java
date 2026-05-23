package com.vsign.backend.common.response;

import java.time.Instant;

public record SuccessResponse<T>(
        boolean success,
        String message,
        T data,
        Instant timestamp
) {
    public static <T> SuccessResponse<T> ok(String message, T data) {
        return new SuccessResponse<>(true, message, data, Instant.now());
    }

    public static <T> SuccessResponse<T> created(String message, T data) {
        return ok(message, data);
    }
}

package com.vsign.backend.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation failed"),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email is already registered"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Invalid email or password"),
    ACCOUNT_DISABLED(HttpStatus.FORBIDDEN, "Account is disabled"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    UNIT_NOT_FOUND(HttpStatus.NOT_FOUND, "Unit not found"),
    CHAPTER_NOT_FOUND(HttpStatus.NOT_FOUND, "Chapter not found"),
    LESSON_NOT_FOUND(HttpStatus.NOT_FOUND, "Lesson not found"),
    PREMIUM_REQUIRED(HttpStatus.FORBIDDEN, "Premium access is required"),
    ATTEMPT_NOT_FOUND(HttpStatus.NOT_FOUND, "Attempt not found"),
    ATTEMPT_ALREADY_SUBMITTED(HttpStatus.CONFLICT, "Attempt is already submitted"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final HttpStatus status;
    private final String defaultMessage;

    ErrorCode(HttpStatus status, String defaultMessage) {
        this.status = status;
        this.defaultMessage = defaultMessage;
    }

    public HttpStatus status() {
        return status;
    }

    public String defaultMessage() {
        return defaultMessage;
    }
}

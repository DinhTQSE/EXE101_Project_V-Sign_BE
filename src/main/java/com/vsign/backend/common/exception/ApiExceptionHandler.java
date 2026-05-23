package com.vsign.backend.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusiness(BusinessException ex, HttpServletRequest request) {
        return build(ex.errorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(IllegalArgumentException ex, HttpServletRequest request) {
        return build(ErrorCode.INVALID_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<ApiErrorResponse.ValidationError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ApiErrorResponse.ValidationError(error.getField(), error.getDefaultMessage()))
                .toList();

        ApiErrorResponse body = new ApiErrorResponse(
                java.time.Instant.now(),
                ErrorCode.VALIDATION_ERROR.status().value(),
                ErrorCode.VALIDATION_ERROR.status().getReasonPhrase(),
                ErrorCode.VALIDATION_ERROR.name(),
                ErrorCode.VALIDATION_ERROR.defaultMessage(),
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.status(ErrorCode.VALIDATION_ERROR.status()).body(body);
    }

    @ExceptionHandler({NoSuchElementException.class, NoResourceFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleNotFound(Exception ex, HttpServletRequest request) {
        return build(ErrorCode.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleInternal(Exception ex, HttpServletRequest request) {
        return build(ErrorCode.INTERNAL_ERROR, ErrorCode.INTERNAL_ERROR.defaultMessage(), request);
    }

    private static ResponseEntity<ApiErrorResponse> build(
            ErrorCode code,
            String message,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(code.status())
                .body(ApiErrorResponse.of(code, message, request.getRequestURI()));
    }
}

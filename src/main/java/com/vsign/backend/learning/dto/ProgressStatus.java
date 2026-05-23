package com.vsign.backend.learning.dto;

import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import java.util.Locale;

public enum ProgressStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED;

    public static ProgressStatus parse(String value, ProgressStatus defaultStatus) {
        if (value == null || value.isBlank()) {
            return defaultStatus;
        }

        try {
            return ProgressStatus.valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new BusinessException(
                    ErrorCode.VALIDATION_ERROR,
                    "status must be NOT_STARTED, IN_PROGRESS, or COMPLETED"
            );
        }
    }
}

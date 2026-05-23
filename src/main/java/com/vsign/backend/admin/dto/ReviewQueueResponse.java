package com.vsign.backend.admin.dto;

import java.util.List;

public record ReviewQueueResponse(
        String requiredRole,
        List<ReviewQueueItemResponse> items
) {
}

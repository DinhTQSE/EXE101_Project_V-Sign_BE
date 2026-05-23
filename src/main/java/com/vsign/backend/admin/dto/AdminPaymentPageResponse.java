package com.vsign.backend.admin.dto;

import java.util.List;

public record AdminPaymentPageResponse(
        int page,
        int size,
        int totalElements,
        List<AdminPaymentRecordResponse> items
) {
}

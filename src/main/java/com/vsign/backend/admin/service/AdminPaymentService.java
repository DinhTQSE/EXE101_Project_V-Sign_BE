package com.vsign.backend.admin.service;

import com.vsign.backend.admin.dto.AdminKpiResponse;
import com.vsign.backend.admin.dto.AdminPaymentPageResponse;
import com.vsign.backend.admin.dto.AdminPaymentRecordResponse;
import com.vsign.backend.admin.dto.ManualPaymentStatusRequest;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminPaymentService {

    private final List<AdminPaymentRecordResponse> payments = new ArrayList<>(List.of(
            new AdminPaymentRecordResponse("txn-1001", "user-1001", "MOMO", "PENDING", 199000, "2026-05-20T10:00:00Z", "2026-05-20T10:00:00Z"),
            new AdminPaymentRecordResponse("txn-1002", "user-1002", "ZALOPAY", "PAID", 199000, "2026-05-19T10:00:00Z", "2026-05-19T10:03:00Z"),
            new AdminPaymentRecordResponse("txn-1003", "user-1003", "MOMO", "FAILED", 199000, "2026-05-18T10:00:00Z", "2026-05-18T10:05:00Z")
    ));

    private final AdminAuditService auditService;

    public AdminPaymentService(AdminAuditService auditService) {
        this.auditService = auditService;
    }

    public AdminPaymentPageResponse listPayments(String requesterRole, Integer page, Integer size) {
        requireAdminRole(requesterRole);
        int resolvedPage = page == null ? 0 : page;
        int resolvedSize = size == null ? 20 : size;
        if (resolvedPage < 0 || resolvedSize <= 0 || resolvedSize > 100) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "page or size is invalid");
        }
        int from = Math.min(resolvedPage * resolvedSize, payments.size());
        int to = Math.min(from + resolvedSize, payments.size());
        return new AdminPaymentPageResponse(resolvedPage, resolvedSize, payments.size(), payments.subList(from, to));
    }

    public AdminPaymentRecordResponse overrideStatus(
            String requesterRole,
            String actorEmail,
            String transactionId,
            ManualPaymentStatusRequest request
    ) {
        requireAdminRole(requesterRole);
        String status = request.status().trim().toUpperCase();
        if (!status.equals("PENDING") && !status.equals("PAID") && !status.equals("FAILED") && !status.equals("EXPIRED")) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "status must be PENDING, PAID, FAILED, or EXPIRED");
        }
        String reason = request.reason().trim();
        if (reason.length() < 5) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "reason must contain at least 5 characters");
        }

        for (int i = 0; i < payments.size(); i++) {
            AdminPaymentRecordResponse existing = payments.get(i);
            if (existing.transactionId().equals(transactionId)) {
                AdminPaymentRecordResponse updated = new AdminPaymentRecordResponse(
                        existing.transactionId(),
                        existing.userId(),
                        existing.provider(),
                        status,
                        existing.amountVnd(),
                        existing.createdAt(),
                        Instant.now().toString()
                );
                payments.set(i, updated);
                auditService.log(actorEmail, "PAYMENT_STATUS_OVERRIDE", transactionId, reason);
                return updated;
            }
        }
        throw new BusinessException(ErrorCode.NOT_FOUND, "Payment transaction not found");
    }

    public AdminKpiResponse getKpis(String requesterRole, String fromDate, String toDate) {
        requireAdminRole(requesterRole);
        long revenue = payments.stream()
                .filter(payment -> payment.status().equals("PAID"))
                .mapToLong(AdminPaymentRecordResponse::amountVnd)
                .sum();
        int successCount = (int) payments.stream().filter(payment -> payment.status().equals("PAID")).count();
        return new AdminKpiResponse(fromDate, toDate, 4, 2, revenue, successCount);
    }

    private static void requireAdminRole(String role) {
        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            throw new BusinessException(ErrorCode.FORBIDDEN, "ADMIN role is required");
        }
    }
}

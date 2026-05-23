package com.vsign.backend.admin.service;

import com.vsign.backend.admin.dto.AdminAuditLogResponse;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminAuditService {

    private final List<AdminAuditLogResponse> entries = new ArrayList<>();

    public synchronized void log(String actorEmail, String action, String targetId, String reason) {
        entries.add(new AdminAuditLogResponse(
                "audit-" + (entries.size() + 1),
                actorEmail,
                action,
                targetId,
                reason,
                Instant.now().toString()
        ));
    }

    public synchronized List<AdminAuditLogResponse> list(String requesterRole) {
        requireAdminRole(requesterRole);
        return List.copyOf(entries);
    }

    private static void requireAdminRole(String role) {
        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            throw new BusinessException(ErrorCode.FORBIDDEN, "ADMIN role is required");
        }
    }
}

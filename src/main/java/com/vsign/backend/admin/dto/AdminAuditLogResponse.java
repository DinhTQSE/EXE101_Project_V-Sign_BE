package com.vsign.backend.admin.dto;

public record AdminAuditLogResponse(
        String auditId,
        String actorEmail,
        String action,
        String targetId,
        String reason,
        String createdAt
) {
}

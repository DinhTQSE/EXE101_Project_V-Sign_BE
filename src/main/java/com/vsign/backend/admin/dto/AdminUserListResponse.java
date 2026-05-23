package com.vsign.backend.admin.dto;

import java.util.List;

public record AdminUserListResponse(
        String requiredRole,
        String appliedRoleFilter,
        String appliedStatusFilter,
        List<AdminUserResponse> users
) {
}

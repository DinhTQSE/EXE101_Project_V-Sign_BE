package com.vsign.backend.admin.service;

import com.vsign.backend.admin.dto.AdminUserListResponse;
import com.vsign.backend.admin.dto.AdminUserResponse;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    private static final List<AdminUserResponse> USERS = List.of(
            new AdminUserResponse("user-1001", "learner.one@vsign.test", "Learner One", "USER", "ACTIVE", 7, 420),
            new AdminUserResponse("user-1002", "learner.two@vsign.test", "Learner Two", "USER", "ACTIVE", 3, 180),
            new AdminUserResponse("admin-9001", "admin@vsign.test", "Admin Operator", "ADMIN", "ACTIVE", 0, 0),
            new AdminUserResponse("user-1003", "paused@vsign.test", "Paused Learner", "USER", "SUSPENDED", 0, 95)
    );

    /**
     * Security metadata only: the endpoint is intended for ADMIN role enforcement
     * once shared security policy is wired.
     */
    public AdminUserListResponse listUsers(String requesterRole, String role, String status) {
        requireAdminRole(requesterRole);
        List<AdminUserResponse> filtered = USERS.stream()
                .filter(user -> role == null || role.isBlank() || user.role().equalsIgnoreCase(role))
                .filter(user -> status == null || status.isBlank() || user.status().equalsIgnoreCase(status))
                .toList();

        return new AdminUserListResponse("ADMIN", role, status, filtered);
    }

    private static void requireAdminRole(String role) {
        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            throw new BusinessException(ErrorCode.FORBIDDEN, "ADMIN role is required");
        }
    }
}

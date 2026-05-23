package com.vsign.backend.admin.controller;

import com.vsign.backend.admin.dto.AdminUserListResponse;
import com.vsign.backend.admin.service.AdminUserService;
import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    public SuccessResponse<AdminUserListResponse> listUsers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status,
            HttpServletRequest request
    ) {
        String requesterRole = (String) request.getAttribute(JwtAuthFilter.AUTH_ROLE_ATTRIBUTE);
        return SuccessResponse.ok("Admin users retrieved", adminUserService.listUsers(requesterRole, role, status));
    }
}

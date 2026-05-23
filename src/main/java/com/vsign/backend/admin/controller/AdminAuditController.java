package com.vsign.backend.admin.controller;

import com.vsign.backend.admin.dto.AdminAuditLogResponse;
import com.vsign.backend.admin.service.AdminAuditService;
import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/audit-logs")
public class AdminAuditController {

    private final AdminAuditService adminAuditService;

    public AdminAuditController(AdminAuditService adminAuditService) {
        this.adminAuditService = adminAuditService;
    }

    @GetMapping
    public SuccessResponse<List<AdminAuditLogResponse>> listAuditLogs(HttpServletRequest request) {
        String requesterRole = (String) request.getAttribute(JwtAuthFilter.AUTH_ROLE_ATTRIBUTE);
        return SuccessResponse.ok("Admin audit logs retrieved", adminAuditService.list(requesterRole));
    }
}

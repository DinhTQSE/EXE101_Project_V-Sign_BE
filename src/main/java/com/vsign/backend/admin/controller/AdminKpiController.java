package com.vsign.backend.admin.controller;

import com.vsign.backend.admin.dto.AdminKpiResponse;
import com.vsign.backend.admin.service.AdminPaymentService;
import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/kpis")
public class AdminKpiController {

    private final AdminPaymentService adminPaymentService;

    public AdminKpiController(AdminPaymentService adminPaymentService) {
        this.adminPaymentService = adminPaymentService;
    }

    @GetMapping
    public SuccessResponse<AdminKpiResponse> getKpis(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            HttpServletRequest request
    ) {
        String requesterRole = (String) request.getAttribute(JwtAuthFilter.AUTH_ROLE_ATTRIBUTE);
        return SuccessResponse.ok("Admin KPI summary retrieved", adminPaymentService.getKpis(requesterRole, fromDate, toDate));
    }
}

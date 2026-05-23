package com.vsign.backend.admin.controller;

import com.vsign.backend.admin.dto.AdminPaymentPageResponse;
import com.vsign.backend.admin.dto.AdminPaymentRecordResponse;
import com.vsign.backend.admin.dto.ManualPaymentStatusRequest;
import com.vsign.backend.admin.service.AdminPaymentService;
import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/payments")
public class AdminPaymentController {

    private final AdminPaymentService adminPaymentService;

    public AdminPaymentController(AdminPaymentService adminPaymentService) {
        this.adminPaymentService = adminPaymentService;
    }

    @GetMapping
    public SuccessResponse<AdminPaymentPageResponse> listPayments(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            HttpServletRequest request
    ) {
        String requesterRole = (String) request.getAttribute(JwtAuthFilter.AUTH_ROLE_ATTRIBUTE);
        return SuccessResponse.ok("Admin payments retrieved", adminPaymentService.listPayments(requesterRole, page, size));
    }

    @PatchMapping("/{transactionId}")
    public SuccessResponse<AdminPaymentRecordResponse> overridePaymentStatus(
            @PathVariable String transactionId,
            @Valid @RequestBody ManualPaymentStatusRequest requestBody,
            HttpServletRequest request
    ) {
        String requesterRole = (String) request.getAttribute(JwtAuthFilter.AUTH_ROLE_ATTRIBUTE);
        String actorEmail = (String) request.getAttribute(JwtAuthFilter.AUTH_EMAIL_ATTRIBUTE);
        return SuccessResponse.ok(
                "Payment status updated",
                adminPaymentService.overrideStatus(requesterRole, actorEmail, transactionId, requestBody)
        );
    }
}

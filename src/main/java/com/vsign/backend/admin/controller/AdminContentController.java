package com.vsign.backend.admin.controller;

import com.vsign.backend.admin.dto.ReviewDecisionRequest;
import com.vsign.backend.admin.dto.ReviewQueueItemResponse;
import com.vsign.backend.admin.dto.ReviewQueueResponse;
import com.vsign.backend.admin.service.AdminContentReviewService;
import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/content")
public class AdminContentController {

    private final AdminContentReviewService adminContentReviewService;

    public AdminContentController(AdminContentReviewService adminContentReviewService) {
        this.adminContentReviewService = adminContentReviewService;
    }

    @GetMapping("/review-queue")
    public SuccessResponse<ReviewQueueResponse> listReviewQueue(HttpServletRequest request) {
        String requesterRole = (String) request.getAttribute(JwtAuthFilter.AUTH_ROLE_ATTRIBUTE);
        return SuccessResponse.ok("Content review queue retrieved", adminContentReviewService.listReviewQueue(requesterRole));
    }

    @PatchMapping("/review-queue/{contentId}")
    public SuccessResponse<ReviewQueueItemResponse> decideReviewQueueItem(
            @PathVariable String contentId,
            @Valid @RequestBody ReviewDecisionRequest requestBody,
            HttpServletRequest request
    ) {
        String requesterRole = (String) request.getAttribute(JwtAuthFilter.AUTH_ROLE_ATTRIBUTE);
        String actorEmail = (String) request.getAttribute(JwtAuthFilter.AUTH_EMAIL_ATTRIBUTE);
        return SuccessResponse.ok(
                "Content review decision applied",
                adminContentReviewService.decide(requesterRole, actorEmail, contentId, requestBody)
        );
    }
}

package com.vsign.backend.admin.service;

import com.vsign.backend.admin.dto.ReviewDecisionRequest;
import com.vsign.backend.admin.dto.ReviewQueueItemResponse;
import com.vsign.backend.admin.dto.ReviewQueueResponse;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminContentReviewService {

    private final List<ReviewQueueItemResponse> queue = new ArrayList<>(List.of(
            new ReviewQueueItemResponse("doc-2001", "DOCUMENT_UPLOAD", "user-1001", "Emergency Sign Language Notes", "PENDING_REVIEW", "HIGH", "2026-05-21T09:00:00Z"),
            new ReviewQueueItemResponse("sign-3001", "SIGN_DICTIONARY_ENTRY", "user-1002", "School Vocabulary Submission", "PENDING_REVIEW", "MEDIUM", "2026-05-20T14:30:00Z"),
            new ReviewQueueItemResponse("doc-2002", "DOCUMENT_UPLOAD", "user-1003", "Healthcare Practice Worksheet", "NEEDS_REVISION", "LOW", "2026-05-19T11:15:00Z")
    ));

    private final AdminAuditService auditService;

    public AdminContentReviewService(AdminAuditService auditService) {
        this.auditService = auditService;
    }

    public ReviewQueueResponse listReviewQueue(String requesterRole) {
        requireReviewerOrAdmin(requesterRole);
        return new ReviewQueueResponse("CONTENT_REVIEWER_OR_ADMIN", List.copyOf(queue));
    }

    public ReviewQueueItemResponse decide(
            String requesterRole,
            String actorEmail,
            String contentId,
            ReviewDecisionRequest request
    ) {
        requireAdminRole(requesterRole);
        String decision = request.decision().trim().toUpperCase();
        if (!decision.equals("APPROVED") && !decision.equals("NEEDS_REVISION") && !decision.equals("REJECTED")) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "decision must be APPROVED, NEEDS_REVISION, or REJECTED");
        }

        for (int i = 0; i < queue.size(); i++) {
            ReviewQueueItemResponse existing = queue.get(i);
            if (existing.contentId().equals(contentId)) {
                ReviewQueueItemResponse updated = new ReviewQueueItemResponse(
                        existing.contentId(),
                        existing.contentType(),
                        existing.submittedBy(),
                        existing.title(),
                        decision,
                        existing.priority(),
                        existing.submittedAt()
                );
                queue.set(i, updated);
                auditService.log(actorEmail, "CONTENT_REVIEW_DECISION", contentId, request.reason().trim());
                return updated;
            }
        }
        throw new BusinessException(ErrorCode.NOT_FOUND, "Review queue item not found");
    }

    private static void requireReviewerOrAdmin(String role) {
        if (role == null || !(role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("CONTENT_REVIEWER"))) {
            throw new BusinessException(ErrorCode.FORBIDDEN, "CONTENT_REVIEWER or ADMIN role is required");
        }
    }

    private static void requireAdminRole(String role) {
        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            throw new BusinessException(ErrorCode.FORBIDDEN, "ADMIN role is required");
        }
    }
}

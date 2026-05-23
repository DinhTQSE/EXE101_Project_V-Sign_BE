package com.vsign.backend.assessment.controller;

import com.vsign.backend.assessment.dto.AssessmentDetailResponse;
import com.vsign.backend.assessment.dto.AssessmentSubmissionRequest;
import com.vsign.backend.assessment.dto.AssessmentSubmissionResultResponse;
import com.vsign.backend.assessment.dto.AssessmentSummaryResponse;
import com.vsign.backend.assessment.service.AssessmentService;
import com.vsign.backend.common.response.SuccessResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public SuccessResponse<List<AssessmentSummaryResponse>> listAssessments() {
        return SuccessResponse.ok("Assessments retrieved", assessmentService.listAssessments());
    }

    @GetMapping("/{id}")
    public SuccessResponse<AssessmentDetailResponse> getAssessment(@PathVariable String id) {
        return SuccessResponse.ok("Assessment detail retrieved", assessmentService.getAssessment(id));
    }

    @PostMapping("/{id}/submissions")
    public SuccessResponse<AssessmentSubmissionResultResponse> submitAssessment(
            @PathVariable String id,
            @RequestBody AssessmentSubmissionRequest request
    ) {
        return SuccessResponse.ok("Assessment submission evaluated", assessmentService.submit(id, request));
    }
}

package com.vsign.backend.assessment.controller;

import com.vsign.backend.assessment.dto.QuizResultResponse;
import com.vsign.backend.assessment.dto.QuizReviewResponse;
import com.vsign.backend.assessment.dto.SubmitAttemptRequest;
import com.vsign.backend.assessment.service.QuizAttemptService;
import com.vsign.backend.common.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    public QuizAttemptController(QuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @PostMapping("/api/v1/quiz-attempts/{attemptId}/submit")
    public SuccessResponse<QuizResultResponse> submitAttempt(
            @PathVariable String attemptId,
            @RequestBody SubmitAttemptRequest request
    ) {
        return SuccessResponse.ok("Quiz attempt submitted", quizAttemptService.submitAttempt(attemptId, request));
    }

    @GetMapping("/api/v1/quiz-attempts/{attemptId}/review")
    public SuccessResponse<QuizReviewResponse> reviewAttempt(@PathVariable String attemptId) {
        return SuccessResponse.ok("Quiz attempt review retrieved", quizAttemptService.reviewAttempt(attemptId));
    }
}

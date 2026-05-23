package com.vsign.backend.learning.controller;

import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.learning.dto.PracticeItemDetailResponse;
import com.vsign.backend.learning.dto.PracticeItemsPageResponse;
import com.vsign.backend.learning.service.LearningWorkflowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/learning")
public class LearningController {

    private final LearningWorkflowService learningWorkflowService;

    public LearningController(LearningWorkflowService learningWorkflowService) {
        this.learningWorkflowService = learningWorkflowService;
    }

    @GetMapping("/practice-items")
    public SuccessResponse<PracticeItemsPageResponse> listPracticeItems(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return SuccessResponse.ok(
                "Practice items retrieved",
                learningWorkflowService.listPracticeItems(category, level, page, size)
        );
    }

    @GetMapping("/practice-items/{itemId}")
    public SuccessResponse<PracticeItemDetailResponse> getPracticeItem(@PathVariable String itemId) {
        return SuccessResponse.ok("Practice item retrieved", learningWorkflowService.getPracticeItem(itemId));
    }
}

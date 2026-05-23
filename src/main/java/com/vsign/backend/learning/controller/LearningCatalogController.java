package com.vsign.backend.learning.controller;

import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.learning.dto.ChapterListResponse;
import com.vsign.backend.learning.dto.LessonDetailResponse;
import com.vsign.backend.learning.dto.LessonListResponse;
import com.vsign.backend.learning.dto.ProgressResponse;
import com.vsign.backend.learning.dto.UnitListResponse;
import com.vsign.backend.learning.dto.UnitSearchRequest;
import com.vsign.backend.learning.dto.UpdateProgressRequest;
import com.vsign.backend.learning.service.LearningWorkflowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LearningCatalogController {

    private final LearningWorkflowService learningWorkflowService;

    public LearningCatalogController(LearningWorkflowService learningWorkflowService) {
        this.learningWorkflowService = learningWorkflowService;
    }

    @GetMapping("/units")
    public SuccessResponse<UnitListResponse> listUnits(
            @RequestParam(required = false) Boolean publishedOnly,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        UnitSearchRequest request = new UnitSearchRequest(publishedOnly, page, size);
        return SuccessResponse.ok("Learning units retrieved", learningWorkflowService.listUnits(request));
    }

    @GetMapping("/units/{unitId}/chapters")
    public SuccessResponse<ChapterListResponse> listChapters(@PathVariable String unitId) {
        return SuccessResponse.ok("Unit chapters retrieved", learningWorkflowService.listChapters(unitId));
    }

    @GetMapping("/chapters/{chapterId}/lessons")
    public SuccessResponse<LessonListResponse> listLessons(@PathVariable String chapterId) {
        return SuccessResponse.ok("Chapter lessons retrieved", learningWorkflowService.listLessons(chapterId));
    }

    @GetMapping("/lessons/{lessonId}")
    public SuccessResponse<LessonDetailResponse> getLesson(@PathVariable String lessonId) {
        return SuccessResponse.ok("Lesson retrieved", learningWorkflowService.getLesson(lessonId));
    }

    @PutMapping("/lessons/{lessonId}/progress")
    public SuccessResponse<ProgressResponse> updateProgress(
            @PathVariable String lessonId,
            @RequestBody(required = false) UpdateProgressRequest request
    ) {
        return SuccessResponse.ok("Lesson progress updated", learningWorkflowService.updateProgress(lessonId, request));
    }
}

package com.vsign.backend.learning;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LearningWorkflowIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listsPracticeItemsWithPagingAndFilters() throws Exception {
        mockMvc.perform(get("/api/v1/learning/practice-items")
                        .param("category", "greeting")
                        .param("level", "beginner")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.page").value(0))
                .andExpect(jsonPath("$.data.size").value(2))
                .andExpect(jsonPath("$.data.totalElements").value(2))
                .andExpect(jsonPath("$.data.totalPages").value(1))
                .andExpect(jsonPath("$.data.content.length()").value(2))
                .andExpect(jsonPath("$.data.content[0].itemId").value("practice-hello"))
                .andExpect(jsonPath("$.data.content[0].category").value("greeting"))
                .andExpect(jsonPath("$.data.content[0].level").value("beginner"))
                .andExpect(jsonPath("$.data.content[0].sourceVideoFile").value("W00489.mp4"));
    }

    @Test
    void preservesPracticeItemTotalPagesForOutOfRangePage() throws Exception {
        mockMvc.perform(get("/api/v1/learning/practice-items")
                        .param("category", "greeting")
                        .param("level", "beginner")
                        .param("page", "5")
                        .param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.page").value(5))
                .andExpect(jsonPath("$.data.size").value(1))
                .andExpect(jsonPath("$.data.totalElements").value(2))
                .andExpect(jsonPath("$.data.totalPages").value(2))
                .andExpect(jsonPath("$.data.content.length()").value(0));
    }

    @Test
    void getsPracticeItemDetail() throws Exception {
        mockMvc.perform(get("/api/v1/learning/practice-items/practice-sorry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.itemId").value("practice-sorry"))
                .andExpect(jsonPath("$.data.lessonId").value("lesson-greetings-1"))
                .andExpect(jsonPath("$.data.expectedGloss").value("XIN_LOI"))
                .andExpect(jsonPath("$.data.sourceVideoFile").value("W03990.mp4"))
                .andExpect(jsonPath("$.data.rubric.length()").value(3));
    }

    @Test
    void returnsNotFoundForUnknownPracticeItem() throws Exception {
        mockMvc.perform(get("/api/v1/learning/practice-items/missing-item"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("NOT_FOUND"));
    }

    @Test
    void submitsSignatureWorkflowAttempt() throws Exception {
        mockMvc.perform(post("/api/v1/signature-workflows/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userStoryId": "US-LRN-003",
                                  "practiceItemId": "practice-hello",
                                  "documentUploadId": "doc-upload-001",
                                  "signatureVector": "right-hand-open-forward",
                                  "durationMs": 3200
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.attemptId").isString())
                .andExpect(jsonPath("$.data.practiceItemId").value("practice-hello"))
                .andExpect(jsonPath("$.data.status").value("SUBMITTED"))
                .andExpect(jsonPath("$.data.score").value(86))
                .andExpect(jsonPath("$.data.feedbackCodes[0]").value("HAND_SHAPE_MATCH"));
    }

    @Test
    void submitsSignatureWorkflowAttemptWithAiPredictionMetadata() throws Exception {
        mockMvc.perform(post("/api/v1/signature-workflows/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userStoryId": "US-AI-REAL-001",
                                  "practiceItemId": "practice-hello",
                                  "signatureVector": "ai:HELLO:0.93:24:21",
                                  "durationMs": 2800,
                                  "aiStatus": "ok",
                                  "targetGloss": "XIN_CHAO",
                                  "predictedGloss": "XIN_CHAO",
                                  "confidence": 0.93,
                                  "correct": true,
                                  "framesProcessed": 24,
                                  "handsDetectedFrames": 21,
                                  "inferenceMs": 312.5
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.status").value("PASSED"))
                .andExpect(jsonPath("$.data.score").value(93))
                .andExpect(jsonPath("$.data.targetGloss").value("XIN_CHAO"))
                .andExpect(jsonPath("$.data.predictedGloss").value("XIN_CHAO"))
                .andExpect(jsonPath("$.data.confidence").value(0.93))
                .andExpect(jsonPath("$.data.correct").value(true))
                .andExpect(jsonPath("$.data.feedbackCodes[0]").value("SIGN_MATCH"));
    }

    @Test
    void rejectsAttemptForMissingSignatureVector() throws Exception {
        mockMvc.perform(post("/api/v1/signature-workflows/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "practiceItemId": "practice-hello",
                                  "documentUploadId": "doc-upload-001",
                                  "durationMs": 3200
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void listsLearningUnitsWithCatalogMetadata() throws Exception {
        mockMvc.perform(get("/api/v1/units")
                        .param("publishedOnly", "true")
                        .param("page", "0")
                        .param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.page").value(0))
                .andExpect(jsonPath("$.data.size").value(1))
                .andExpect(jsonPath("$.data.totalElements").value(8))
                .andExpect(jsonPath("$.data.totalPages").value(8))
                .andExpect(jsonPath("$.data.units.length()").value(1))
                .andExpect(jsonPath("$.data.units[0].unitId").value("unit-basics"))
                .andExpect(jsonPath("$.data.units[0].chapterCount").value(2))
                .andExpect(jsonPath("$.data.units[0].orderIndex").value(1));
    }

    @Test
    void rejectsInvalidUnitPagingBounds() throws Exception {
        mockMvc.perform(get("/api/v1/units")
                        .param("page", "-1")
                        .param("size", "101"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void listsChapterAndLessonPremiumLockMetadata() throws Exception {
        mockMvc.perform(get("/api/v1/units/unit-basics/chapters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.unitId").value("unit-basics"))
                .andExpect(jsonPath("$.data.chapters[0].chapterId").value("chapter-greetings"))
                .andExpect(jsonPath("$.data.chapters[1].chapterId").value("chapter-daily-life"))
                .andExpect(jsonPath("$.data.chapters[1].requiresPremium").value(false));

        mockMvc.perform(get("/api/v1/units/unit-education-places/chapters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.chapters[1].chapterId").value("chapter-places"))
                .andExpect(jsonPath("$.data.chapters[1].requiresPremium").value(true))
                .andExpect(jsonPath("$.data.chapters[1].locked").value(true));

        mockMvc.perform(get("/api/v1/chapters/chapter-greetings/lessons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.chapterId").value("chapter-greetings"))
                .andExpect(jsonPath("$.data.lessons[0].lessonId").value("lesson-greetings-1"))
                .andExpect(jsonPath("$.data.lessons[0].requiresPremium").value(false))
                .andExpect(jsonPath("$.data.lessons[1].locked").value(true));
    }

    @Test
    void getsLessonDetailWithResumeCheckpoint() throws Exception {
        mockMvc.perform(get("/api/v1/lessons/lesson-greetings-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.lessonId").value("lesson-greetings-1"))
                .andExpect(jsonPath("$.data.requiresPremium").value(false))
                .andExpect(jsonPath("$.data.progress.lastPositionSeconds").value(42))
                .andExpect(jsonPath("$.data.progress.phase").value("PRACTICE"))
                .andExpect(jsonPath("$.data.progress.currentQuestionIndex").value(1));
    }

    @Test
    void blocksPremiumLessonDetailWithoutLeakingVideo() throws Exception {
        mockMvc.perform(get("/api/v1/lessons/lesson-places-1"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value("PREMIUM_REQUIRED"))
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.videoUrl").doesNotExist());
    }

    @Test
    void blocksProgressUpdateForPremiumLesson() throws Exception {
        mockMvc.perform(put("/api/v1/lessons/lesson-places-1/progress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "completionPct": 20,
                                  "lastPositionSeconds": 15,
                                  "status": "IN_PROGRESS"
                                }
                                """))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value("PREMIUM_REQUIRED"));
    }

    @Test
    void updatesLessonProgressIdempotently() throws Exception {
        String progressBody = """
                {
                  "completionPct": 65,
                  "lastPositionSeconds": 144,
                  "phase": "QUIZ",
                  "currentQuestionIndex": 2,
                  "status": "IN_PROGRESS"
                }
                """;

        mockMvc.perform(put("/api/v1/lessons/lesson-greetings-1/progress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(progressBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.lessonId").value("lesson-greetings-1"))
                .andExpect(jsonPath("$.data.completionPct").value(65))
                .andExpect(jsonPath("$.data.lastPositionSeconds").value(144))
                .andExpect(jsonPath("$.data.phase").value("QUIZ"))
                .andExpect(jsonPath("$.data.currentQuestionIndex").value(2));

        mockMvc.perform(put("/api/v1/lessons/lesson-greetings-1/progress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(progressBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.lessonId").value("lesson-greetings-1"))
                .andExpect(jsonPath("$.data.completionPct").value(65))
                .andExpect(jsonPath("$.data.status").value("IN_PROGRESS"));
    }

    @Test
    void rejectsInvalidProgressAndMissingLesson() throws Exception {
        mockMvc.perform(put("/api/v1/lessons/lesson-greetings-1/progress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "completionPct": 101,
                                  "lastPositionSeconds": -1
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));

        mockMvc.perform(get("/api/v1/lessons/missing-lesson"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("LESSON_NOT_FOUND"));
    }

    @Test
    void rejectsUnknownProgressStatus() throws Exception {
        mockMvc.perform(put("/api/v1/lessons/lesson-greetings-1/progress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "completionPct": 40,
                                  "lastPositionSeconds": 35,
                                  "status": "PAUSED_FOREVER"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test
    void usesDomainCodesForMissingCatalogRecords() throws Exception {
        mockMvc.perform(get("/api/v1/units/missing-unit/chapters"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("UNIT_NOT_FOUND"));

        mockMvc.perform(get("/api/v1/chapters/missing-chapter/lessons"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("CHAPTER_NOT_FOUND"));
    }
}

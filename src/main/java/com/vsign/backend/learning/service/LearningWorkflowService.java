package com.vsign.backend.learning.service;

import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import com.vsign.backend.learning.dto.ChapterListResponse;
import com.vsign.backend.learning.dto.ChapterSummaryResponse;
import com.vsign.backend.learning.dto.LessonDetailResponse;
import com.vsign.backend.learning.dto.LessonListResponse;
import com.vsign.backend.learning.dto.LessonProgressCheckpointResponse;
import com.vsign.backend.learning.dto.LessonSummaryResponse;
import com.vsign.backend.learning.dto.PracticeItemDetailResponse;
import com.vsign.backend.learning.dto.PracticeItemSummaryResponse;
import com.vsign.backend.learning.dto.PracticeItemsPageResponse;
import com.vsign.backend.learning.dto.ProgressStatus;
import com.vsign.backend.learning.dto.ProgressResponse;
import com.vsign.backend.learning.dto.SignatureAttemptResponse;
import com.vsign.backend.learning.dto.SubmitSignatureAttemptRequest;
import com.vsign.backend.learning.dto.UnitListResponse;
import com.vsign.backend.learning.dto.UnitSummaryResponse;
import com.vsign.backend.learning.dto.UnitSearchRequest;
import com.vsign.backend.learning.dto.UpdateProgressRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class LearningWorkflowService {

    private static final List<PracticeItemDetailResponse> PRACTICE_ITEMS = List.of(
            new PracticeItemDetailResponse(
                    "practice-hello",
                    "lesson-greetings-1",
                    "Say Hello",
                    "greeting",
                    "beginner",
                    "HELLO",
                    "Raise your dominant hand and move it outward from the forehead.",
                    List.of("HAND_SHAPE_MATCH", "MOVEMENT_DIRECTION", "FACE_ORIENTATION")
            ),
            new PracticeItemDetailResponse(
                    "practice-thank-you",
                    "lesson-greetings-1",
                    "Say Thank You",
                    "greeting",
                    "beginner",
                    "THANK_YOU",
                    "Touch your chin with a flat hand and move the hand forward.",
                    List.of("HAND_SHAPE_MATCH", "START_POSITION", "MOVEMENT_FLUENCY")
            ),
            new PracticeItemDetailResponse(
                    "practice-school",
                    "lesson-places-1",
                    "Sign School",
                    "place",
                    "intermediate",
                    "SCHOOL",
                    "Clap both flat hands together twice with steady rhythm.",
                    List.of("BOTH_HANDS_VISIBLE", "CONTACT_TIMING", "REPETITION_COUNT")
            )
    );

    private static final List<UnitRecord> UNITS = List.of(
            new UnitRecord(new UnitSummaryResponse(
                    "unit-basics",
                    "V-Sign Basics",
                    "https://cdn.vsign.test/units/basics.png",
                    2,
                    1
            ), true),
            new UnitRecord(new UnitSummaryResponse(
                    "unit-everyday",
                    "Everyday Conversations",
                    "https://cdn.vsign.test/units/everyday.png",
                    1,
                    2
            ), true)
    );

    private static final List<ChapterRecord> CHAPTERS = List.of(
            new ChapterRecord("chapter-greetings", "unit-basics", "Greetings", 1, false, false, 58),
            new ChapterRecord("chapter-places", "unit-basics", "Places", 2, true, true, 0),
            new ChapterRecord("chapter-family", "unit-everyday", "Family", 1, false, false, 12)
    );

    private static final List<LessonRecord> LESSONS = List.of(
            new LessonRecord("lesson-greetings-1", "chapter-greetings", "Hello and Thank You", 1, 360,
                    false, false, "IN_PROGRESS", 35, "https://cdn.vsign.test/lessons/greetings-1.m3u8"),
            new LessonRecord("lesson-greetings-2", "chapter-greetings", "Meet and Introduce", 2, 420,
                    false, true, "LOCKED", 0, "https://cdn.vsign.test/lessons/greetings-2.m3u8"),
            new LessonRecord("lesson-places-1", "chapter-places", "School and Home", 1, 390,
                    true, true, "LOCKED", 0, "https://cdn.vsign.test/lessons/places-1.m3u8"),
            new LessonRecord("lesson-family-1", "chapter-family", "Family Members", 1, 410,
                    false, false, "NOT_STARTED", 0, "https://cdn.vsign.test/lessons/family-1.m3u8")
    );

    public PracticeItemsPageResponse listPracticeItems(String category, String level, int page, int size) {
        int normalizedPage = Math.max(page, 0);
        int normalizedSize = size <= 0 ? 10 : size;

        List<PracticeItemSummaryResponse> filtered = PRACTICE_ITEMS.stream()
                .filter(item -> matches(item.category(), category))
                .filter(item -> matches(item.level(), level))
                .map(this::toSummary)
                .toList();

        int totalPages = pageCount(filtered.size(), normalizedSize);
        int fromIndex = normalizedPage * normalizedSize;
        if (fromIndex >= filtered.size()) {
            return new PracticeItemsPageResponse(List.of(), normalizedPage, normalizedSize, filtered.size(), totalPages);
        }

        int toIndex = Math.min(fromIndex + normalizedSize, filtered.size());
        return new PracticeItemsPageResponse(
                filtered.subList(fromIndex, toIndex),
                normalizedPage,
                normalizedSize,
                filtered.size(),
                totalPages
        );
    }

    public PracticeItemDetailResponse getPracticeItem(String itemId) {
        return PRACTICE_ITEMS.stream()
                .filter(item -> item.itemId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Practice item not found: " + itemId));
    }

    public SignatureAttemptResponse submitAttempt(SubmitSignatureAttemptRequest request) {
        validateAttempt(request);
        PracticeItemDetailResponse item = getPracticeItem(request.practiceItemId().trim());
        String attemptId = UUID.nameUUIDFromBytes((request.practiceItemId() + ':' + request.signatureVector())
                .getBytes(StandardCharsets.UTF_8)).toString();

        return new SignatureAttemptResponse(
                attemptId,
                blankToDefault(request.userStoryId(), "US-LRN-003"),
                item.itemId(),
                request.documentUploadId().trim(),
                "SUBMITTED",
                scoreFor(request.signatureVector()),
                List.of(item.rubric().get(0), "REVIEW_READY")
        );
    }

    public UnitListResponse listUnits(UnitSearchRequest request) {
        UnitSearchRequest normalized = request == null ? new UnitSearchRequest(true, 0, 10) : request;
        int page = normalized.page() == null ? 0 : normalized.page();
        int size = normalized.size() == null ? 10 : normalized.size();
        validateUnitSearch(page, size);

        boolean publishedOnly = normalized.publishedOnly() == null || normalized.publishedOnly();
        List<UnitSummaryResponse> filtered = UNITS.stream()
                .filter(unit -> !publishedOnly || unit.published())
                .map(UnitRecord::summary)
                .toList();
        int fromIndex = page * size;
        int totalPages = pageCount(filtered.size(), size);
        if (fromIndex >= filtered.size()) {
            return new UnitListResponse(List.of(), page, size, filtered.size(), totalPages);
        }

        int toIndex = Math.min(fromIndex + size, filtered.size());
        return new UnitListResponse(
                filtered.subList(fromIndex, toIndex),
                page,
                size,
                filtered.size(),
                totalPages
        );
    }

    public ChapterListResponse listChapters(String unitId) {
        findUnit(unitId);
        List<ChapterSummaryResponse> chapters = CHAPTERS.stream()
                .filter(chapter -> chapter.unitId().equals(unitId))
                .map(chapter -> new ChapterSummaryResponse(
                        chapter.chapterId(),
                        chapter.title(),
                        chapter.orderIndex(),
                        countLessons(chapter.chapterId()),
                        chapter.requiresPremium(),
                        chapter.locked(),
                        chapter.progressPct()
                ))
                .toList();
        return new ChapterListResponse(unitId, chapters);
    }

    public LessonListResponse listLessons(String chapterId) {
        findChapter(chapterId);
        List<LessonSummaryResponse> lessons = LESSONS.stream()
                .filter(lesson -> lesson.chapterId().equals(chapterId))
                .map(lesson -> new LessonSummaryResponse(
                        lesson.lessonId(),
                        lesson.title(),
                        lesson.orderIndex(),
                        lesson.durationSeconds(),
                        lesson.requiresPremium(),
                        lesson.locked(),
                        lesson.status(),
                        lesson.progressPct()
                ))
                .toList();
        return new LessonListResponse(chapterId, lessons);
    }

    public LessonDetailResponse getLesson(String lessonId) {
        LessonRecord lesson = findLesson(lessonId);
        if (lesson.requiresPremium()) {
            throw new BusinessException(
                    ErrorCode.PREMIUM_REQUIRED,
                    "Premium access is required for lesson: " + lessonId
            );
        }
        return new LessonDetailResponse(
                lesson.lessonId(),
                lesson.chapterId(),
                lesson.title(),
                lesson.videoUrl(),
                lesson.requiresPremium(),
                lesson.locked(),
                defaultCheckpointFor(lesson)
        );
    }

    public ProgressResponse updateProgress(String lessonId, UpdateProgressRequest request) {
        LessonRecord lesson = findLesson(lessonId);
        if (lesson.requiresPremium()) {
            throw new BusinessException(
                    ErrorCode.PREMIUM_REQUIRED,
                    "Premium access is required for lesson: " + lessonId
            );
        }
        validateProgress(request);
        ProgressStatus defaultStatus = defaultProgressStatus(request.completionPct());
        ProgressStatus status = ProgressStatus.parse(request.status(), defaultStatus);
        return new ProgressResponse(
                lessonId,
                request.completionPct(),
                request.lastPositionSeconds(),
                blankToDefault(request.phase(), "VIDEO"),
                request.currentQuestionIndex() == null ? 0 : request.currentQuestionIndex(),
                status.name()
        );
    }

    private PracticeItemSummaryResponse toSummary(PracticeItemDetailResponse item) {
        return new PracticeItemSummaryResponse(
                item.itemId(),
                item.lessonId(),
                item.title(),
                item.category(),
                item.level(),
                "beginner".equals(item.level()) ? 120 : 180
        );
    }

    private boolean matches(String actual, String expected) {
        return expected == null || expected.isBlank()
                || actual.equalsIgnoreCase(expected.trim());
    }

    private UnitRecord findUnit(String unitId) {
        return UNITS.stream()
                .filter(unit -> unit.summary().unitId().equals(unitId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.UNIT_NOT_FOUND, "Unit not found: " + unitId));
    }

    private ChapterRecord findChapter(String chapterId) {
        return CHAPTERS.stream()
                .filter(chapter -> chapter.chapterId().equals(chapterId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.CHAPTER_NOT_FOUND, "Chapter not found: " + chapterId));
    }

    private LessonRecord findLesson(String lessonId) {
        return LESSONS.stream()
                .filter(lesson -> lesson.lessonId().equals(lessonId))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.LESSON_NOT_FOUND, "Lesson not found: " + lessonId));
    }

    private int countLessons(String chapterId) {
        return (int) LESSONS.stream()
                .filter(lesson -> lesson.chapterId().equals(chapterId))
                .count();
    }

    private LessonProgressCheckpointResponse defaultCheckpointFor(LessonRecord lesson) {
        if ("lesson-greetings-1".equals(lesson.lessonId())) {
            return new LessonProgressCheckpointResponse(35, 42, "PRACTICE", 1, "IN_PROGRESS");
        }
        return new LessonProgressCheckpointResponse(lesson.progressPct(), 0, "VIDEO", 0, lesson.status());
    }

    private void validateProgress(UpdateProgressRequest request) {
        if (request == null || request.completionPct() == null || request.lastPositionSeconds() == null) {
            throw new BusinessException(
                    ErrorCode.VALIDATION_ERROR,
                    "completionPct and lastPositionSeconds are required"
            );
        }
        if (request.completionPct() < 0 || request.completionPct() > 100) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "completionPct must be between 0 and 100");
        }
        if (request.lastPositionSeconds() < 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "lastPositionSeconds must be non-negative");
        }
        if (request.currentQuestionIndex() != null && request.currentQuestionIndex() < 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "currentQuestionIndex must be non-negative");
        }
    }

    private ProgressStatus defaultProgressStatus(int completionPct) {
        return completionPct == 100 ? ProgressStatus.COMPLETED : ProgressStatus.IN_PROGRESS;
    }

    private void validateUnitSearch(int page, int size) {
        if (page < 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "page must be non-negative");
        }
        if (size < 1 || size > 100) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "size must be between 1 and 100");
        }
    }

    private int pageCount(int totalElements, int size) {
        return (int) Math.ceil((double) totalElements / size);
    }

    private void validateAttempt(SubmitSignatureAttemptRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Request body is required");
        }
        if (isBlank(request.practiceItemId())) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "practiceItemId is required");
        }
        if (isBlank(request.documentUploadId())) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "documentUploadId is required");
        }
        if (isBlank(request.signatureVector())) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "signatureVector is required");
        }
        if (request.durationMs() == null || request.durationMs() <= 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "durationMs must be greater than zero");
        }
    }

    private int scoreFor(String signatureVector) {
        String normalized = signatureVector.toLowerCase(Locale.ROOT);
        if (normalized.contains("right-hand") || normalized.contains("flat")) {
            return 86;
        }
        return 72;
    }

    private String blankToDefault(String value, String defaultValue) {
        return isBlank(value) ? defaultValue : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private record ChapterRecord(
            String chapterId,
            String unitId,
            String title,
            int orderIndex,
            boolean requiresPremium,
            boolean locked,
            int progressPct
    ) {
    }

    private record UnitRecord(UnitSummaryResponse summary, boolean published) {
    }

    private record LessonRecord(
            String lessonId,
            String chapterId,
            String title,
            int orderIndex,
            int durationSeconds,
            boolean requiresPremium,
            boolean locked,
            String status,
            int progressPct,
            String videoUrl
    ) {
    }
}

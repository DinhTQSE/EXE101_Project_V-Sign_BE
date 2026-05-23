package com.vsign.backend.assessment.service;

import com.vsign.backend.assessment.dto.OptionResponse;
import com.vsign.backend.assessment.dto.QuestionResponse;
import com.vsign.backend.assessment.dto.QuizAnswerRequest;
import com.vsign.backend.assessment.dto.QuizResponse;
import com.vsign.backend.assessment.dto.QuizResultResponse;
import com.vsign.backend.assessment.dto.QuizReviewQuestionResponse;
import com.vsign.backend.assessment.dto.QuizReviewResponse;
import com.vsign.backend.assessment.dto.SubmitAttemptRequest;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class QuizAttemptService {

    private static final String PREMIUM_LESSON_ID = "lesson-premium-conversation";

    private static final QuizDefinition GREETINGS_QUIZ = new QuizDefinition(
            "quiz-greetings",
            "lesson-greetings",
            "Greeting Recognition Check",
            70,
            180,
            List.of(
                    new QuizQuestion(
                            "quiz-q-hello",
                            "Choose the sign that means Hello.",
                            "answer-hello",
                            "Hello is commonly presented with a greeting motion.",
                            List.of(option("answer-hello", "Hello"), option("answer-water", "Water"))
                    ),
                    new QuizQuestion(
                            "quiz-q-thanks",
                            "Choose the sign that means Thank you.",
                            "answer-thanks",
                            "Thank you moves outward from the chin.",
                            List.of(option("answer-thanks", "Thank you"), option("answer-thanks-wrong", "School"))
                    )
            )
    );

    private final Map<String, AttemptState> attempts = new ConcurrentHashMap<>();

    public QuizAttemptService() {
        attempts.put("attempt-greetings-ready", AttemptState.ready("attempt-greetings-ready", GREETINGS_QUIZ));
        attempts.put("attempt-greetings-partial", AttemptState.ready("attempt-greetings-partial", GREETINGS_QUIZ));
        attempts.put("attempt-greetings-mixed", AttemptState.ready("attempt-greetings-mixed", GREETINGS_QUIZ));
        attempts.put(
                "attempt-greetings-reviewed",
                AttemptState.submitted(
                        "attempt-greetings-reviewed",
                        GREETINGS_QUIZ,
                        Map.of("quiz-q-hello", "answer-hello", "quiz-q-thanks", "answer-thanks-wrong")
                )
        );
    }

    public QuizResponse getLessonQuiz(String lessonId) {
        if (PREMIUM_LESSON_ID.equals(lessonId)) {
            throw new BusinessException(ErrorCode.PREMIUM_REQUIRED, "Premium access is required for this lesson quiz");
        }
        if (!GREETINGS_QUIZ.lessonId().equals(lessonId)) {
            throw new BusinessException(ErrorCode.LESSON_NOT_FOUND, "Lesson quiz not found");
        }
        String attemptId = "attempt-" + UUID.randomUUID();
        attempts.put(attemptId, AttemptState.ready(attemptId, GREETINGS_QUIZ));
        return GREETINGS_QUIZ.toResponse(attemptId);
    }

    public QuizResultResponse submitAttempt(String attemptId, SubmitAttemptRequest request) {
        AttemptState attempt = findAttempt(attemptId);
        if (attempt.submitted()) {
            throw new BusinessException(ErrorCode.ATTEMPT_ALREADY_SUBMITTED, "Quiz attempt already submitted");
        }

        validateSubmitRequest(request);
        Map<String, QuizAnswerRequest> answersByQuestionId = request.answers().stream()
                .collect(Collectors.toMap(QuizAnswerRequest::questionId, Function.identity(), (first, ignored) -> first));
        ensureAnswersBelongToQuiz(attempt.quiz(), answersByQuestionId);
        ensureSelectedAnswersBelongToQuestions(attempt.quiz(), answersByQuestionId);

        Map<String, String> selectedAnswers = attempt.quiz().questions().stream()
                .filter(question -> answersByQuestionId.containsKey(question.id()))
                .collect(Collectors.toMap(
                        QuizQuestion::id,
                        question -> answersByQuestionId.get(question.id()).selectedAnswerId(),
                        (first, ignored) -> first,
                        LinkedHashMap::new
                ));
        if (!attempt.markSubmitted(selectedAnswers)) {
            throw new BusinessException(ErrorCode.ATTEMPT_ALREADY_SUBMITTED, "Quiz attempt already submitted");
        }

        int correctCount = correctAnswerCount(attempt);
        int unansweredCount = attempt.quiz().questions().size() - selectedAnswers.size();
        int score = Math.round(correctCount * 100.0f / attempt.quiz().questions().size());
        boolean timedOut = request.durationSeconds() != null
                && request.durationSeconds() > attempt.quiz().timeLimitSeconds();
        return new QuizResultResponse(
                attempt.id(),
                attempt.quiz().id(),
                score,
                score >= attempt.quiz().passingScore(),
                correctCount * 10,
                true,
                timedOut,
                unansweredCount
        );
    }

    public QuizReviewResponse reviewAttempt(String attemptId) {
        AttemptState attempt = findAttempt(attemptId);
        if (!attempt.submitted()) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Quiz attempt must be submitted before review");
        }
        List<QuizReviewQuestionResponse> questions = attempt.quiz().questions().stream()
                .map(question -> toReviewQuestion(question, attempt.selectedAnswers().get(question.id())))
                .toList();
        return new QuizReviewResponse(attempt.id(), attempt.quiz().id(), questions);
    }

    private static OptionResponse option(String id, String label) {
        return new OptionResponse(id, label);
    }

    private static QuizReviewQuestionResponse toReviewQuestion(QuizQuestion question, String selectedAnswerId) {
        return new QuizReviewQuestionResponse(
                question.id(),
                question.prompt(),
                selectedAnswerId,
                question.correctAnswerId(),
                question.correctAnswerId().equals(selectedAnswerId),
                question.explanation()
        );
    }

    private static int correctAnswerCount(AttemptState attempt) {
        return (int) attempt.quiz().questions().stream()
                .filter(question -> question.correctAnswerId().equals(attempt.selectedAnswers().get(question.id())))
                .count();
    }

    private static void validateSubmitRequest(SubmitAttemptRequest request) {
        if (request == null || request.answers() == null) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Quiz answers are required");
        }
        boolean hasInvalidAnswer = request.answers().stream()
                .anyMatch(answer -> answer == null
                        || answer.questionId() == null
                        || answer.questionId().isBlank()
                        || answer.selectedAnswerId() == null
                        || answer.selectedAnswerId().isBlank());
        if (hasInvalidAnswer) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Quiz answers require question and answer ids");
        }
        if (request.durationSeconds() != null && request.durationSeconds() < 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Quiz duration cannot be negative");
        }
    }

    private static void ensureAnswersBelongToQuiz(QuizDefinition quiz, Map<String, QuizAnswerRequest> answersByQuestionId) {
        boolean hasUnknownQuestion = answersByQuestionId.keySet().stream()
                .anyMatch(questionId -> quiz.questions().stream().noneMatch(question -> question.id().equals(questionId)));
        if (hasUnknownQuestion) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Quiz answer contains unknown question id");
        }
    }

    private static void ensureSelectedAnswersBelongToQuestions(
            QuizDefinition quiz,
            Map<String, QuizAnswerRequest> answersByQuestionId
    ) {
        boolean hasUnknownAnswer = quiz.questions().stream()
                .filter(question -> answersByQuestionId.containsKey(question.id()))
                .anyMatch(question -> question.options().stream()
                        .noneMatch(option -> option.id().equals(
                                answersByQuestionId.get(question.id()).selectedAnswerId()
                        )));
        if (hasUnknownAnswer) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Quiz answer does not belong to question");
        }
    }

    private AttemptState findAttempt(String attemptId) {
        AttemptState attempt = attempts.get(attemptId);
        if (attempt == null) {
            throw new BusinessException(ErrorCode.ATTEMPT_NOT_FOUND, "Quiz attempt not found");
        }
        return attempt;
    }

    private record QuizDefinition(
            String id,
            String lessonId,
            String title,
            int passingScore,
            int timeLimitSeconds,
            List<QuizQuestion> questions
    ) {
        QuizResponse toResponse(String attemptId) {
            return new QuizResponse(
                    id,
                    lessonId,
                    attemptId,
                    title,
                    passingScore,
                    timeLimitSeconds,
                    questions.stream().map(QuizQuestion::toResponse).toList()
            );
        }
    }

    private record QuizQuestion(
            String id,
            String prompt,
            String correctAnswerId,
            String explanation,
            List<OptionResponse> options
    ) {
        QuestionResponse toResponse() {
            return new QuestionResponse(
                    id,
                    prompt,
                    "multiple-choice",
                    "/media/quizzes/" + id + ".mp4",
                    options
            );
        }
    }

    private static final class AttemptState {
        private final String id;
        private final QuizDefinition quiz;
        private boolean submitted;
        private Map<String, String> selectedAnswers;

        private AttemptState(String id, QuizDefinition quiz, boolean submitted, Map<String, String> selectedAnswers) {
            this.id = id;
            this.quiz = quiz;
            this.submitted = submitted;
            this.selectedAnswers = new LinkedHashMap<>(selectedAnswers);
        }

        static AttemptState ready(String id, QuizDefinition quiz) {
            return new AttemptState(id, quiz, false, Map.of());
        }

        static AttemptState submitted(String id, QuizDefinition quiz, Map<String, String> selectedAnswers) {
            return new AttemptState(id, quiz, true, selectedAnswers);
        }

        String id() {
            return id;
        }

        QuizDefinition quiz() {
            return quiz;
        }

        synchronized boolean submitted() {
            return submitted;
        }

        synchronized Map<String, String> selectedAnswers() {
            return selectedAnswers;
        }

        synchronized boolean markSubmitted(Map<String, String> answers) {
            if (submitted) {
                return false;
            }
            submitted = true;
            selectedAnswers = new LinkedHashMap<>(answers);
            return true;
        }
    }
}

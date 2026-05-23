package com.vsign.backend.assessment.service;

import com.vsign.backend.assessment.dto.AnswerRequest;
import com.vsign.backend.assessment.dto.AssessmentDetailResponse;
import com.vsign.backend.assessment.dto.AssessmentSubmissionRequest;
import com.vsign.backend.assessment.dto.AssessmentSubmissionResultResponse;
import com.vsign.backend.assessment.dto.AssessmentSummaryResponse;
import com.vsign.backend.assessment.dto.OptionResponse;
import com.vsign.backend.assessment.dto.QuestionResponse;
import com.vsign.backend.assessment.dto.QuestionResultResponse;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

    private static final List<AssessmentDefinition> ASSESSMENTS = List.of(
            new AssessmentDefinition(
                    "asl-basics-placement",
                    "ASL Basics Placement",
                    "Checks recognition of beginner vocabulary used by the onboarding path.",
                    "beginner",
                    70,
                    8,
                    List.of(
                            question("q-hello", "Select the sign meaning Hello", "hello"),
                            question("q-thank-you", "Select the sign meaning Thank you", "thank-you"),
                            question("q-school", "Select the sign meaning School", "school")
                    )
            ),
            new AssessmentDefinition(
                    "daily-conversation-check",
                    "Daily Conversation Check",
                    "Measures readiness for common greeting and daily-need conversations.",
                    "beginner",
                    75,
                    10,
                    List.of(
                            question("q-water", "Select the sign meaning Water", "water"),
                            question("q-yesterday", "Select the sign meaning Yesterday", "yesterday")
                    )
            ),
            new AssessmentDefinition(
                    "place-vocabulary-review",
                    "Place Vocabulary Review",
                    "Reviews place-related vocabulary before moving into travel lessons.",
                    "intermediate",
                    80,
                    12,
                    List.of(
                            question("q-hospital", "Select the sign meaning Hospital", "hospital"),
                            question("q-school-review", "Select the sign meaning School", "school")
                    )
            )
    );

    private static QuestionDefinition question(String id, String prompt, String correctOptionId) {
        return new QuestionDefinition(
                id,
                prompt,
                "multiple-choice",
                "/media/signs/" + correctOptionId + ".mp4",
                correctOptionId,
                List.of(
                        new OptionResponse(correctOptionId, titleCase(correctOptionId)),
                        new OptionResponse("distractor-greeting", "Greeting"),
                        new OptionResponse("distractor-place", "Place")
                )
        );
    }

    public List<AssessmentSummaryResponse> listAssessments() {
        return ASSESSMENTS.stream()
                .map(AssessmentDefinition::toSummary)
                .toList();
    }

    public AssessmentDetailResponse getAssessment(String id) {
        return findAssessment(id).toDetail();
    }

    public AssessmentSubmissionResultResponse submit(String assessmentId, AssessmentSubmissionRequest request) {
        AssessmentDefinition assessment = findAssessment(assessmentId);
        validateSubmission(request);

        Map<String, QuestionDefinition> questionsById = assessment.questions().stream()
                .collect(Collectors.toMap(QuestionDefinition::id, Function.identity()));
        Map<String, AnswerRequest> answersByQuestionId = request.answers().stream()
                .collect(Collectors.toMap(AnswerRequest::questionId, Function.identity(), (first, ignored) -> first));

        List<QuestionResultResponse> results = assessment.questions().stream()
                .map(question -> evaluateQuestion(question, answersByQuestionId.get(question.id())))
                .toList();

        ensureOnlyKnownQuestions(answersByQuestionId, questionsById);

        int correctAnswers = (int) results.stream().filter(QuestionResultResponse::correct).count();
        int score = Math.round(correctAnswers * 100.0f / assessment.questions().size());
        boolean passed = score >= assessment.passingScore();
        int awardedXp = correctAnswers * 10;
        return new AssessmentSubmissionResultResponse(
                assessment.id(),
                request.userId().trim(),
                score,
                passed,
                correctAnswers,
                assessment.questions().size(),
                awardedXp,
                results
        );
    }

    private static QuestionResultResponse evaluateQuestion(QuestionDefinition question, AnswerRequest answer) {
        String selectedOptionId = answer == null ? null : answer.selectedOptionId();
        boolean correct = question.correctOptionId().equals(selectedOptionId);
        return new QuestionResultResponse(question.id(), selectedOptionId, question.correctOptionId(), correct);
    }

    private static void ensureOnlyKnownQuestions(
            Map<String, AnswerRequest> answersByQuestionId,
            Map<String, QuestionDefinition> questionsById
    ) {
        boolean hasUnknownQuestion = answersByQuestionId.keySet().stream()
                .anyMatch(questionId -> !questionsById.containsKey(questionId));
        if (hasUnknownQuestion) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Submission contains unknown question id");
        }
    }

    private static void validateSubmission(AssessmentSubmissionRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Submission body is required");
        }
        if (request.userId() == null || request.userId().isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "userId is required");
        }
        if (request.answers() == null || request.answers().isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "At least one answer is required");
        }
        boolean hasBlankAnswer = request.answers().stream()
                .anyMatch(answer -> answer == null
                        || answer.questionId() == null
                        || answer.questionId().isBlank()
                        || answer.selectedOptionId() == null
                        || answer.selectedOptionId().isBlank());
        if (hasBlankAnswer) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Each answer must include questionId and selectedOptionId");
        }
    }

    private static AssessmentDefinition findAssessment(String id) {
        if (id == null || id.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Assessment id is required");
        }
        return ASSESSMENTS.stream()
                .filter(assessment -> assessment.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Assessment not found"));
    }

    private static String titleCase(String value) {
        String[] parts = value.split("-");
        return java.util.Arrays.stream(parts)
                .map(part -> part.substring(0, 1).toUpperCase() + part.substring(1))
                .collect(Collectors.joining(" "));
    }

    private record AssessmentDefinition(
            String id,
            String title,
            String description,
            String difficulty,
            int passingScore,
            int estimatedMinutes,
            List<QuestionDefinition> questions
    ) {
        AssessmentSummaryResponse toSummary() {
            return new AssessmentSummaryResponse(
                    id,
                    title,
                    description,
                    difficulty,
                    questions.size(),
                    passingScore,
                    estimatedMinutes
            );
        }

        AssessmentDetailResponse toDetail() {
            return new AssessmentDetailResponse(
                    id,
                    title,
                    description,
                    difficulty,
                    passingScore,
                    estimatedMinutes,
                    questions.stream().map(QuestionDefinition::toResponse).toList()
            );
        }
    }

    private record QuestionDefinition(
            String id,
            String prompt,
            String type,
            String mediaUrl,
            String correctOptionId,
            List<OptionResponse> options
    ) {
        QuestionResponse toResponse() {
            return new QuestionResponse(id, prompt, type, mediaUrl, options);
        }
    }
}

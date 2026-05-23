package com.vsign.backend.dictionary.service;

import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import com.vsign.backend.dictionary.dto.DictionaryEntryResponse;
import com.vsign.backend.dictionary.dto.PracticeTargetResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    private static final List<DictionaryEntryResponse> ENTRIES = List.of(
            new DictionaryEntryResponse("1", "Hello", "Raise hand and move outward", "greeting", 1),
            new DictionaryEntryResponse("2", "Thank you", "Touch chin then move hand forward", "greeting", 1),
            new DictionaryEntryResponse("3", "Hospital", "Form H-hands and tap twice", "place", 3),
            new DictionaryEntryResponse("4", "School", "Clap flat hands together twice", "place", 2),
            new DictionaryEntryResponse("5", "Water", "Tap W-hand near mouth", "daily", 1),
            new DictionaryEntryResponse("6", "Yesterday", "Thumb from cheek backward", "time", 2)
    );

    private static final Map<String, PracticeTargetResponse> PRACTICE_TARGETS = Map.of(
            "1", new PracticeTargetResponse(
                    "1",
                    "unit-greetings",
                    "chapter-basic-greetings",
                    "lesson-hello",
                    "quiz-greetings-1",
                    false
            ),
            "2", new PracticeTargetResponse(
                    "2",
                    "unit-greetings",
                    "chapter-basic-greetings",
                    "lesson-thank-you",
                    "quiz-greetings-1",
                    false
            ),
            "3", new PracticeTargetResponse(
                    "3",
                    "unit-places",
                    "chapter-community-places",
                    "lesson-hospital",
                    "quiz-places-advanced",
                    true
            ),
            "4", new PracticeTargetResponse(
                    "4",
                    "unit-places",
                    "chapter-community-places",
                    "lesson-school",
                    "quiz-places-basic",
                    false
            ),
            "5", new PracticeTargetResponse(
                    "5",
                    "unit-daily-life",
                    "chapter-daily-needs",
                    "lesson-water",
                    "quiz-daily-1",
                    false
            ),
            "6", new PracticeTargetResponse(
                    "6",
                    "unit-time",
                    "chapter-past-present",
                    "lesson-yesterday",
                    "quiz-time-1",
                    false
            )
    );

    public DictionaryEntryPage findEntries(
            String category,
            String keyword,
            Integer difficulty,
            int page,
            int size
    ) {
        validateSearchRequest(category, keyword, difficulty, page, size);

        List<DictionaryEntryResponse> filtered = ENTRIES.stream()
                .filter(entry -> matchesCategory(entry, category))
                .filter(entry -> matchesKeyword(entry, keyword))
                .filter(entry -> matchesDifficulty(entry, difficulty))
                .toList();

        int totalPages = (int) Math.ceil((double) filtered.size() / size);
        int fromIndex = page * size;
        if (fromIndex >= filtered.size()) {
            return new DictionaryEntryPage(List.of(), page, size, filtered.size(), totalPages);
        }

        int toIndex = Math.min(fromIndex + size, filtered.size());
        return new DictionaryEntryPage(
                filtered.subList(fromIndex, toIndex),
                page,
                size,
                filtered.size(),
                totalPages
        );
    }

    public PracticeTargetResponse findPracticeTarget(String entryId) {
        if (entryId == null || entryId.isBlank()) {
            throw validation("entryId is required");
        }

        PracticeTargetResponse target = PRACTICE_TARGETS.get(entryId.trim());
        if (target == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND, "Dictionary entry was not found");
        }

        return target;
    }

    private void validateSearchRequest(
            String category,
            String keyword,
            Integer difficulty,
            int page,
            int size
    ) {
        if (keyword != null && keyword.trim().length() > 100) {
            throw validation("keyword must be 100 characters or fewer");
        }
        if (page < 0) {
            throw validation("page must be greater than or equal to 0");
        }
        if (size < 1 || size > 100) {
            throw validation("size must be between 1 and 100");
        }
        if (difficulty != null && (difficulty < 1 || difficulty > 3)) {
            throw validation("difficulty must be between 1 and 3");
        }
        if (category != null && !category.isBlank() && ENTRIES.stream().noneMatch(entry -> matchesCategory(entry, category))) {
            throw validation("category is not supported");
        }
    }

    private BusinessException validation(String message) {
        return new BusinessException(ErrorCode.VALIDATION_ERROR, message);
    }

    private boolean matchesCategory(DictionaryEntryResponse entry, String category) {
        return category == null || category.isBlank()
                || entry.category().equalsIgnoreCase(category.trim());
    }

    private boolean matchesKeyword(DictionaryEntryResponse entry, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return true;
        }
        String normalized = keyword.toLowerCase(Locale.ROOT).trim();
        return entry.keyword().toLowerCase(Locale.ROOT).contains(normalized)
                || entry.definition().toLowerCase(Locale.ROOT).contains(normalized);
    }

    private boolean matchesDifficulty(DictionaryEntryResponse entry, Integer difficulty) {
        return difficulty == null || difficulty.equals(entry.difficulty());
    }

    public record DictionaryEntryPage(
            List<DictionaryEntryResponse> content,
            int page,
            int size,
            int totalElements,
            int totalPages
    ) {
    }
}

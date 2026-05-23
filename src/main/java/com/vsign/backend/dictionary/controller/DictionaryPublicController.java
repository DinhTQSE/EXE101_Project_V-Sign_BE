package com.vsign.backend.dictionary.controller;

import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.dictionary.dto.DictionaryEntryResponse;
import com.vsign.backend.dictionary.dto.PracticeTargetResponse;
import com.vsign.backend.dictionary.service.DictionaryService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryPublicController {

    private final DictionaryService dictionaryService;

    public DictionaryPublicController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping
    public SuccessResponse<DictionaryEntriesPageResponse> getEntries(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        DictionaryService.DictionaryEntryPage result =
                dictionaryService.findEntries(category, keyword, difficulty, page, size);

        DictionaryEntriesPageResponse response = new DictionaryEntriesPageResponse(
                result.content(),
                result.page(),
                result.size(),
                result.totalElements(),
                result.totalPages()
        );
        return SuccessResponse.ok("Dictionary entries retrieved", response);
    }

    @GetMapping("/{entryId}/practice-target")
    public SuccessResponse<PracticeTargetResponse> getPracticeTarget(@PathVariable String entryId) {
        PracticeTargetResponse response = dictionaryService.findPracticeTarget(entryId);
        return SuccessResponse.ok("Dictionary practice target retrieved", response);
    }

    public record DictionaryEntriesPageResponse(
            List<DictionaryEntryResponse> content,
            int page,
            int size,
            int totalElements,
            int totalPages
    ) {
    }
}

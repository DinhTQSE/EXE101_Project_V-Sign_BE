package com.vsign.backend.dictionary.dto;

public record DictionaryEntryResponse(
        String id,
        String keyword,
        String definition,
        String category,
        Integer difficulty
) {
}

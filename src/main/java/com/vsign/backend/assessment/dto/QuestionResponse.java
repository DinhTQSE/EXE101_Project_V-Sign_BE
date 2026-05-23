package com.vsign.backend.assessment.dto;

import java.util.List;

public record QuestionResponse(
        String id,
        String prompt,
        String type,
        String mediaUrl,
        List<OptionResponse> options
) {
}

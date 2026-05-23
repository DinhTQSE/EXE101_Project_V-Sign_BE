package com.vsign.backend.learning.dto;

public record UnitSearchRequest(
        Boolean publishedOnly,
        Integer page,
        Integer size
) {
}

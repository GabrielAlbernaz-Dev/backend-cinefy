package com.gabrielalbernazdev.backend_cinefy.common.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}

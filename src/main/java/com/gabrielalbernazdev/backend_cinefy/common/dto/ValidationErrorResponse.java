package com.gabrielalbernazdev.backend_cinefy.common.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
    int status,
    String error,
    String message,
    LocalDateTime timestamp,
    String path,
    List<FieldErrorResponse> fieldErrors
) {}


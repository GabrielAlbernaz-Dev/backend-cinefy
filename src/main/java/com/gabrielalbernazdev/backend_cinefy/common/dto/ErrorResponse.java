package com.gabrielalbernazdev.backend_cinefy.common.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
    int status,
    String error,
    String message,
    LocalDateTime timestamp,
    String path
) {}


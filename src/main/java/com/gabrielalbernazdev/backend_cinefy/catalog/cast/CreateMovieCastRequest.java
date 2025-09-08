package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateMovieCastRequest(
    @NotNull UUID personId,
    @NotNull CastRole role,
    String characterName
) {}

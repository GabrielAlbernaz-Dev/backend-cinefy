package com.gabrielalbernazdev.backend_cinefy.catalog.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateGenreRequest(
    @NotBlank
    @Size(max = GenreConstants.CODE_MAX_LENGTH)
    String code,

    @NotBlank
    @Size(max = GenreConstants.NAME_MAX_LENGTH)
    String name
) {}

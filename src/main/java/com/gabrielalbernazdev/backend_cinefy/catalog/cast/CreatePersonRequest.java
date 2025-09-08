package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import jakarta.validation.constraints.NotBlank;

public record CreatePersonRequest(
    @NotBlank String name
) {}


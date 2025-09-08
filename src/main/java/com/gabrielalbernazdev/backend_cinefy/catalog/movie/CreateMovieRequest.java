package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import com.gabrielalbernazdev.backend_cinefy.catalog.cast.MovieCast;
import com.gabrielalbernazdev.backend_cinefy.catalog.genre.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

import static com.gabrielalbernazdev.backend_cinefy.catalog.movie.MovieConstants.MIN_YEAR;
import static com.gabrielalbernazdev.backend_cinefy.catalog.movie.MovieConstants.MAX_YEAR;
import static com.gabrielalbernazdev.backend_cinefy.catalog.movie.MovieConstants.MIN_DURATION;

public record CreateMovieRequest(
    @NotBlank
    String title,

    String description,

    @NotNull
    ExhibitionStatus status,

    @NotNull
    Set<Genre> genres,

    @NotNull
    Set<MovieCast> cast,

    @Min(value = MIN_YEAR, message = "The year cannot less than " + MIN_YEAR + ".")
    @Max(value = MAX_YEAR, message = "The year cannot be greater than " + MAX_YEAR + ".")
    Integer releaseYear,

    @Min(value = MIN_DURATION, message = "The duration cannot be less than " + MIN_DURATION + " minute.")
    Integer durationMin,

    @NotNull
    IndicativeRating indicativeRating
) {}

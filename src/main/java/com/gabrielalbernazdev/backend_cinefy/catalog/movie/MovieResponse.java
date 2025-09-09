package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import com.gabrielalbernazdev.backend_cinefy.catalog.cast.MovieCastResponse;
import com.gabrielalbernazdev.backend_cinefy.catalog.genre.GenreResponse;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record MovieResponse(
    UUID id,
    String title,
    String description,
    ExhibitionStatus status,
    Set<GenreResponse> genres,
    Set<MovieCastResponse> cast,
    Integer releaseYear,
    Integer durationMin,
    IndicativeRating indicativeRating
) {
    public static MovieResponse fromEntity(Movie movie) {
        return new MovieResponse(
            movie.getId(),
            movie.getTitle(),
            movie.getDescription(),
            movie.getStatus(),
            movie.getGenres() == null ? new HashSet<>() : movie.getGenres().stream().map(GenreResponse::fromEntity).collect(Collectors.toSet()),
            movie.getCast() == null ? new HashSet<>() : movie.getCast().stream().map(MovieCastResponse::fromEntity).collect(Collectors.toSet()),
            movie.getReleaseYear(),
            movie.getDurationMin(),
            movie.getIndicativeRating()
        );
    }
}

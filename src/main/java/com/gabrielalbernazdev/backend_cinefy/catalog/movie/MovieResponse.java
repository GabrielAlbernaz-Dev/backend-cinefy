package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import com.gabrielalbernazdev.backend_cinefy.catalog.genre.Genre;
import com.gabrielalbernazdev.backend_cinefy.catalog.cast.Person;

import java.util.Set;
import java.util.UUID;

public record MovieResponse(
    UUID id,
    String title,
    String description,
    ExhibitionStatus status,
    Set<Genre> genres,
    Set<Person> cast,
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
            movie.getGenres(),
            movie.getCast(),
            movie.getReleaseYear(),
            movie.getDurationMin(),
            movie.getIndicativeRating()
        );
    }
}


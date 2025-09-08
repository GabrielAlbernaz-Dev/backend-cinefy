package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

public record MovieCastResponse(
    MovieCastId id,
    PersonResponse person,
    CastRole role
) {
    public static MovieCastResponse fromEntity(MovieCast movieCast) {
        return new MovieCastResponse(
            movieCast.getId(),
            PersonResponse.fromEntity(movieCast.getPerson()),
            movieCast.getRole()
        );
    }
}

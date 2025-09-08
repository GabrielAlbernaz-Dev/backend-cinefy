package com.gabrielalbernazdev.backend_cinefy.catalog.genre;

import java.time.LocalDateTime;
import java.util.UUID;

public record GenreResponse(
    UUID id,
    String code,
    String name,
    boolean active,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static GenreResponse fromEntity(Genre genre) {
        return new GenreResponse(
            genre.getId(),
            genre.getCode(),
            genre.getName(),
            genre.isActive(),
            genre.getCreatedAt(),
            genre.getUpdatedAt()
        );
    }
}

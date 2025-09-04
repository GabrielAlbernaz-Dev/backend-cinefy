package com.gabrielalbernazdev.backend_cinefy.catalog.genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
}

package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, UUID> {
}


package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class MovieCastId implements Serializable {
    private UUID movie;
    private UUID person;
    private CastRole role;

    public MovieCastId() {}

    public MovieCastId(UUID movie, UUID person, CastRole role) {
        this.movie = movie;
        this.person = person;
        this.role = role;
    }

    public UUID getMovie() { return movie; }
    public UUID getPerson() { return person; }
    public CastRole getRole() { return role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCastId that = (MovieCastId) o;
        return Objects.equals(movie, that.movie) && Objects.equals(person, that.person) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, person, role);
    }
}

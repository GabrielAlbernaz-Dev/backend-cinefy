package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import com.gabrielalbernazdev.backend_cinefy.catalog.movie.Movie;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@IdClass(MovieCastId.class)
@Entity
@Table(name = "movie_casts")
public class MovieCast implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private CastRole role = CastRole.OTHER;

    @Column(name = "character_name", length = 155)
    private String characterName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    protected MovieCast() {}

    public MovieCast(Movie movie, Person person, CastRole role, String characterName) {
        this.movie = movie;
        this.person = person;
        this.role = role;
        this.characterName = characterName;
    }

    public static MovieCast create(Movie movie, Person person, CastRole role, String characterName) {
        return new MovieCast(movie, person, role, characterName);
    }

    public MovieCastId getId() {
        return new MovieCastId(movie != null ? movie.getId() : null, person != null ? person.getId() : null, role);
    }

    public Movie getMovie() { return movie; }
    public Person getPerson() { return person; }
    public CastRole getRole() { return role; }
    public String getCharacterName() { return characterName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}

package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import com.gabrielalbernazdev.backend_cinefy.catalog.genre.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.gabrielalbernazdev.backend_cinefy.catalog.cast.MovieCast;

import static com.gabrielalbernazdev.backend_cinefy.catalog.movie.MovieConstants.MIN_YEAR;
import static com.gabrielalbernazdev.backend_cinefy.catalog.movie.MovieConstants.MAX_YEAR;
import static com.gabrielalbernazdev.backend_cinefy.catalog.movie.MovieConstants.MIN_DURATION;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false, length = 155)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExhibitionStatus status = ExhibitionStatus.DRAFT_UNRELEASED;

    @ManyToMany
    @JoinTable(
        name = "movies_genres",
        joinColumns = @JoinColumn(name = "movie_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = false)
    )
    public Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieCast> cast = new HashSet<>();

    @Min(value = MIN_YEAR, message = "The year cannot less than " + MIN_YEAR + ".")
    @Max(value = MAX_YEAR, message = "The year cannot be greater than " + MAX_YEAR + ".")
    @Column(name = "release_year", length = 4)
    private Integer releaseYear;

    @Min(value = MIN_DURATION, message = "The duration cannot be less than " + MIN_DURATION + "minutes.")
    @Column(name = "duration_min")
    private Integer durationMin;

    @Convert(converter = IndicativeRatingConverter.class)
    @Column(name = "indicative_rating", length = 3)
    private IndicativeRating indicativeRating;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if(createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    protected Movie() {}

    private Movie(
        String title,
        String description,
        ExhibitionStatus status,
        Set<Genre> genres,
        Set<MovieCast> cast,
        Integer releaseYear,
        Integer duration,
        IndicativeRating indicativeRating
    ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.releaseYear = releaseYear;
        this.durationMin = duration;
        this.indicativeRating = indicativeRating;
        this.genres = genres;
        this.cast = cast;
    }

    public static Movie create(
        String title,
        String description,
        ExhibitionStatus status,
        Set<Genre> genres,
        Set<MovieCast> cast,
        Integer releaseYear,
        Integer duration,
        IndicativeRating indicativeRating
    ) {
        return new Movie(title, description, status, genres, cast, releaseYear, duration, indicativeRating);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ExhibitionStatus getStatus() {
        return status;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<MovieCast> getCast() {
        return cast;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getDurationMin() {
        return durationMin;
    }

    public IndicativeRating getIndicativeRating() {
        return indicativeRating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void updateInfo(String title, String description, Integer duration, IndicativeRating indicativeRating, Set<Genre> genres, Set<MovieCast> cast) {
        this.title = title;
        this.description = description;
        this.durationMin = duration;
        this.indicativeRating = indicativeRating;
        this.genres = genres;
        this.cast = cast;
    }

    public void releasePreview() {
        this.status = status.releasePreview();
    }

    public void startShowing() {
        this.status = status.startShowing();
    }

    public void endExhibition() {
        this.status = status.endExhibition();
    }

    public void putOnHold() {
        this.status = status.putOnHold();
    }

    public void resumePreview() {
        this.status = status.resumePreview();
    }

    public void resumeShowing() {
        this.status = status.resumeShowing();
    }

    public void archive() {
        this.status = status.archive();
    }
}

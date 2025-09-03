package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 155)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExhibitionStatus status = ExhibitionStatus.DRAFT;

    @Column(name = "release_year", length = 4)
    private Integer releaseYear;

    @Column(name = "duration_min")
    private Integer durationMin;

    @Convert(converter = IndicativeRatingConverter.class)
    @Column(name = "indicative_rating", length = 3)
    private IndicativeRating indicativeRating;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

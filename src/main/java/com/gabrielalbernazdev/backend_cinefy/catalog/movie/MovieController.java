package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable UUID id) {
        Movie movie = movieService.findById(id);
        MovieResponse response = MovieResponse.fromEntity(movie);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody CreateMovieRequest request) {
        Movie movie = movieService.create(request);
        MovieResponse response = MovieResponse.fromEntity(movie);
        var location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(movie.getId())
            .toUri();
        return ResponseEntity.created(location).body(response);
    }
}

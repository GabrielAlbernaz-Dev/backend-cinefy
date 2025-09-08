package com.gabrielalbernazdev.backend_cinefy.catalog.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getById(@PathVariable UUID id) {
        Genre genre = genreService.getById(id);
        GenreResponse response = GenreResponse.fromEntity(genre);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<GenreResponse> create(@RequestBody CreateGenreRequest request) {
        Genre createdGenre = genreService.create(request);
        GenreResponse response = GenreResponse.fromEntity(createdGenre);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdGenre.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
}

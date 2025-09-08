package com.gabrielalbernazdev.backend_cinefy.catalog.genre;

import com.gabrielalbernazdev.backend_cinefy.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public Genre getById(UUID id) {
        return genreRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Genre create(CreateGenreRequest request) {
        Genre genre = Genre.create(request.code(), request.name());
        return genreRepository.save(genre);
    }
}

package com.gabrielalbernazdev.backend_cinefy.catalog.genre;

import com.gabrielalbernazdev.backend_cinefy.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public Set<Genre> getByIds(Set<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(genreRepository.findAllById(ids));
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

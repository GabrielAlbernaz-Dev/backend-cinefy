package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import java.util.UUID;

import com.gabrielalbernazdev.backend_cinefy.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public Movie getById(UUID id) {
        return movieRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Movie create(CreateMovieRequest request) {
        Movie movie = Movie.create(
            request.title(),
            request.description(),
            request.status(),
            request.genres(),
            request.cast(),
            request.releaseYear(),
            request.durationMin(),
            request.indicativeRating()
        );
        movieRepository.save(movie);
        return movie;
    }
}

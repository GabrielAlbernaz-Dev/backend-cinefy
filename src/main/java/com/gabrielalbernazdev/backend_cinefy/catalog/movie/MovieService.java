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
    public Movie findById(UUID id) {
        return movieRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Movie create(CreateMovieRequest request) {
        Movie movie = Movie.create(
            request.title(),
            request.description(),
            request.durationMin(),
            request.indicativeRating(),
            request.genres(),
            request.cast(),
            request.status()
        );
        movieRepository.save(movie);
        return movie;
    }
}

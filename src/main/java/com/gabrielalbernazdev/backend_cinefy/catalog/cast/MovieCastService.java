package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import com.gabrielalbernazdev.backend_cinefy.catalog.movie.Movie;
import com.gabrielalbernazdev.backend_cinefy.catalog.movie.MovieService;
import com.gabrielalbernazdev.backend_cinefy.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MovieCastService {
    private final MovieCastRepository movieCastRepository;
    private final PersonService personService;
    private final MovieService movieService;

    @Autowired
    public MovieCastService(MovieCastRepository movieCastRepository, PersonService personService, MovieService movieService) {
        this.movieCastRepository = movieCastRepository;
        this.personService = personService;
        this.movieService = movieService;
    }

    @Transactional(readOnly = true)
    public MovieCast getById(UUID id) {
        return movieCastRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("MovieCast not found: " + id));
    }

    @Transactional
    public MovieCast create(CreateMovieCastRequest request, UUID movieId) {
        Person person = personService.getById(request.personId());
        Movie movie = movieService.getById(movieId);
        MovieCast movieCast = MovieCast.create(
            movie,
            person,
            request.role(),
            request.characterName()
        );
        return movieCastRepository.save(movieCast);
    }
}


package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import java.util.*;
import java.util.stream.Collectors;

import com.gabrielalbernazdev.backend_cinefy.catalog.cast.Person;
import com.gabrielalbernazdev.backend_cinefy.catalog.cast.PersonService;
import com.gabrielalbernazdev.backend_cinefy.catalog.cast.CreateMovieCastRequest;
import com.gabrielalbernazdev.backend_cinefy.catalog.genre.Genre;
import com.gabrielalbernazdev.backend_cinefy.catalog.genre.GenreService;
import com.gabrielalbernazdev.backend_cinefy.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final PersonService personService;

    @Autowired
    public MovieService(
        MovieRepository movieRepository,
        GenreService genreService,
        PersonService personService
    ) {
        this.movieRepository = movieRepository;
        this.genreService = genreService;
        this.personService = personService;
    }

    @Transactional(readOnly = true)
    public Movie getById(UUID id) {
        return movieRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Movie create(CreateMovieRequest request) {
        Set<Genre> genres = genreService.getByIds(request.genres());
        Movie movie = Movie.create(
            request.title(),
            request.description(),
            request.status(),
            genres,
            request.releaseYear(),
            request.durationMin(),
            request.indicativeRating()
        );

        if (request.cast() != null && !request.cast().isEmpty()) {
            Set<UUID> personIds = request.cast().stream()
                    .map(CreateMovieCastRequest::personId)
                    .collect(Collectors.toSet());

            Set<Person> persons = personService.getByIds(personIds);
            Map<UUID, Person> personById = persons.stream()
                    .collect(Collectors.toMap(Person::getId, p -> p));

            for (CreateMovieCastRequest cr : request.cast()) {
                Person p = personById.get(cr.personId());
                movie.addCast(p, cr.role(), cr.characterName());
            }
        }

        return movieRepository.save(movie);
    }
}

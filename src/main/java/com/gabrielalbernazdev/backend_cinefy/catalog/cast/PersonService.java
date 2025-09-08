package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import com.gabrielalbernazdev.backend_cinefy.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(readOnly = true)
    public Person getById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found: " + id));
    }

    @Transactional
    public Person create(CreatePersonRequest request) {
        Person person = Person.create(
            request.name()
        );
        return personRepository.save(person);
    }
}

package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import com.gabrielalbernazdev.backend_cinefy.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

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

    @Transactional(readOnly = true)
    public Set<Person> getByIds(Set<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(personRepository.findAllById(ids));
    }

    @Transactional
    public Person create(CreatePersonRequest request) {
        Person person = Person.create(
            request.name()
        );
        return personRepository.save(person);
    }
}

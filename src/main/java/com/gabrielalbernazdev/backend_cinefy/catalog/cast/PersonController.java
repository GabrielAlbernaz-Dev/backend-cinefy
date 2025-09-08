package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> find(@PathVariable UUID id) {
        Person person = personService.getById(id);
        PersonResponse response = PersonResponse.fromEntity(person);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody CreatePersonRequest request) {
        Person createdPerson = personService.create(request);
        PersonResponse response = PersonResponse.fromEntity(createdPerson);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPerson.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
}

package com.gabrielalbernazdev.backend_cinefy.catalog.cast;

import java.util.UUID;

public record PersonResponse(
    UUID id,
    String name
) {
    public static PersonResponse fromEntity(Person person) {
        return new PersonResponse(person.getId(), person.getName());
    }
}
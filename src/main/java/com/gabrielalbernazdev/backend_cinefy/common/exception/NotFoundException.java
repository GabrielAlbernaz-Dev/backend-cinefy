package com.gabrielalbernazdev.backend_cinefy.common.exception;

public class NotFoundException extends DomainException {
    public NotFoundException() {
        super("Resource not found");
    }
    public NotFoundException(String message) {
        super(message);
    }
}


package com.clinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String details;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, String details) {
        super(message);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}

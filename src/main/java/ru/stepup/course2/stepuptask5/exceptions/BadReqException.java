package ru.stepup.course2.stepuptask5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadReqException extends ResponseStatusException {
    public BadReqException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

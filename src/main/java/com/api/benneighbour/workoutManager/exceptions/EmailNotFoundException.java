package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8595058343626983155L;

    public EmailNotFoundException(String message) {
        super(message);
    }

}

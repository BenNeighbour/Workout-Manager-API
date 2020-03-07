package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class DuplicateVerificationTokenException extends RuntimeException {

    private static final long serialVersionUID = 8799885328300813082L;

    public DuplicateVerificationTokenException(String message) {
        super(message);
    }

}

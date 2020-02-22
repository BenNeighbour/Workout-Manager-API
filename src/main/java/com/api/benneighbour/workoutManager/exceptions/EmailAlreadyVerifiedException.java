package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyVerifiedException extends RuntimeException {

    private static final long serialVersionUID = -6008775604108675754L;

    public EmailAlreadyVerifiedException(String message) {
        super(message);
    }

}

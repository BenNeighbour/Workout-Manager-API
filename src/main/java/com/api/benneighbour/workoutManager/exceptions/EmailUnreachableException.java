package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailUnreachableException extends RuntimeException {

    private static final long serialVersionUID = -3901527660062194605L;

    public EmailUnreachableException(String message) {
        super(message);
    }

}

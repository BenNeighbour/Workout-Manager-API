package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class EmailVerifySentException extends RuntimeException {

    private static final long serialVersionUID = -6342489067187272380L;

    public EmailVerifySentException(String message) {
        super(message);
    }

}

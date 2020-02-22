package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class EmailAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 7501425696830471930L;

    public EmailAlreadyTakenException(String message) {
        super(message);
    }

}

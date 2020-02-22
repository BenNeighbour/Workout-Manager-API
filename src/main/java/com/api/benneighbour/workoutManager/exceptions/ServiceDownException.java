package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceDownException extends RuntimeException {

    private static final long serialVersionUID = -2574214185033727927L;

    public ServiceDownException(String message) {
        super(message);
    }

}

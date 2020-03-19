package com.api.benneighbour.workoutManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class ThemeChangedException extends RuntimeException {

    private static final long serialVersionUID = -1905956064586132946L;

    public ThemeChangedException(String message) {
        super(message);
    }

}

package com.example.vacation_project.exception;

import com.example.vacation_project.exception.handler.PandaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends PandaException {

    private static final int status = HttpStatus.NOT_FOUND.value();
    public NotFoundException(String message) {
        super(status, message);
    }

}

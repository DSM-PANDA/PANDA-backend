package com.example.vacation_project.exception;

import com.example.vacation_project.exception.handler.PandaException;
import org.springframework.http.HttpStatus;

public class ConflictException extends PandaException {

    private static final int status = HttpStatus.CONFLICT.value();
    public ConflictException(String message) {
        super(status, message);
    }

}

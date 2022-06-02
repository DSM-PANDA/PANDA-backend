package com.example.vacation_project.exception;

import com.example.vacation_project.exception.handler.PandaException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends PandaException {

    private static final int status = HttpStatus.UNAUTHORIZED.value();
    public UnauthorizedException(String message) {
        super(status, message);
    }

}
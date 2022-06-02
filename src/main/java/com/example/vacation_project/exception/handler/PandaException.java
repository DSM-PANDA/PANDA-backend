package com.example.vacation_project.exception.handler;

import lombok.Getter;

@Getter
public class PandaException extends RuntimeException {

    private final int status;
    private final String message;

    protected PandaException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}

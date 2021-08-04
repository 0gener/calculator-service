package com.ivansousa.calculatorservice.controller.model;

import lombok.Getter;

@Getter
public class ErrorResponse extends Response {
    private String message;

    public ErrorResponse(String message) {
        super(true);

        this.message = message;
    }
}

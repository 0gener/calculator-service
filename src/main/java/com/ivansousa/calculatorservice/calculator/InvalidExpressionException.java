package com.ivansousa.calculatorservice.calculator;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException() {
        super("invalid expression");
    }
}

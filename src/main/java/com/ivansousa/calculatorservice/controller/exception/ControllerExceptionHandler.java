package com.ivansousa.calculatorservice.controller.exception;

import javax.validation.ConstraintViolationException;

import com.ivansousa.calculatorservice.calculator.InvalidExpressionException;
import com.ivansousa.calculatorservice.controller.model.ErrorResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
            WebRequest request) {
        String message = ex.getConstraintViolations().stream().findFirst().get().getMessage();
        ErrorResponse error = new ErrorResponse(message);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidExpressionException.class)
    public ResponseEntity<Object> handleInvalidExpressionException(InvalidExpressionException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

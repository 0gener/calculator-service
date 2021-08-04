package com.ivansousa.calculatorservice.controller;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.ivansousa.calculatorservice.controller.model.CalculateResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/calculus")
public class CalculusController {
    @GetMapping
    public ResponseEntity<CalculateResponse> calculate(
            @RequestParam(name = "query", required = false) @NotBlank(message = "query must not be null or blank") String query) {
        CalculateResponse responseEntity = new CalculateResponse(BigDecimal.ZERO);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}

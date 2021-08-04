package com.ivansousa.calculatorservice.controller;

import java.math.BigDecimal;
import java.util.Base64;

import javax.validation.constraints.NotBlank;

import com.ivansousa.calculatorservice.controller.model.CalculateResponse;
import com.ivansousa.calculatorservice.util.Calculator;
import com.ivansousa.calculatorservice.validation.Base64String;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping(path = "/calculus")
@AllArgsConstructor
public class CalculusController {
    private Calculator calculator;

    @GetMapping
    public ResponseEntity<CalculateResponse> calculate(
            @RequestParam(name = "query", required = false) @NotBlank(message = "query must not be null or blank") @Base64String(message = "query must be a valid base64 string") String query) {
        String input = new String(Base64.getDecoder().decode(query));
        BigDecimal result = calculator.parse(input);
        CalculateResponse responseEntity = new CalculateResponse(result);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}

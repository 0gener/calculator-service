package com.ivansousa.calculatorservice.controller.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateResponse extends Response {
    private BigDecimal result;

    public CalculateResponse(BigDecimal result) {
        super(false);

        this.result = result;
    }
}

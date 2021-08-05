package com.ivansousa.calculatorservice.config;

import com.ivansousa.calculatorservice.calculator.Calculator;
import com.ivansousa.calculatorservice.config.properties.CalculatorProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CalculatorConfig {
    private CalculatorProperties properties;

    @Bean
    public Calculator calculator() {
        return new Calculator(properties.getScale(), properties.getRoundingMode());
    }
}

package com.ivansousa.calculatorservice.config.properties;

import java.math.RoundingMode;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "calculator")
public class CalculatorProperties {
    @NotNull
    private Integer scale;

    @NotNull
    private RoundingMode roundingMode;
}

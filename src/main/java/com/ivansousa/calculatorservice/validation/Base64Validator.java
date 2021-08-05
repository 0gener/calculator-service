package com.ivansousa.calculatorservice.validation;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64String, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.equals(""))
            return true;

        try {
            Base64.getDecoder().decode(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

package com.ivansousa.calculatorservice.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private Calculator calculator;

    public CalculatorTest() {
        this.calculator = new Calculator(9, RoundingMode.HALF_UP);
    }

    @Test
    public void evaluate_EmptyExpression_ThrowsInvalidExpressionException() throws Exception {
        assertThrows(InvalidExpressionException.class, () -> calculator.evaluate(""));
    }

    @Test
    public void evaluate_InvalidExpression_ThrowsInvalidExpressionException() throws Exception {
        assertThrows(InvalidExpressionException.class, () -> calculator.evaluate("::';"));
    }

    @Test
    public void evaluate_SinglePositiveNumber_ReturnsSinglePositiveNumber() throws Exception {
        BigDecimal result = calculator.evaluate("10");

        assertEquals(new BigDecimal("10"), result);
    }

    @Test
    public void evaluate_SingleNegativeNumber_ReturnsSingleNegativeNumber() throws Exception {
        BigDecimal result = calculator.evaluate("-10");

        assertEquals(new BigDecimal("-10"), result);
    }

    @Test
    public void evaluate_PositiveDecimalNumber_ReturnsPositiveDecimalNumber() throws Exception {
        BigDecimal result = calculator.evaluate("0.003");

        assertEquals(new BigDecimal("0.003"), result);
    }

    @Test
    public void evaluate_NegativeDecimalNumber_ReturnsNegativeDecimalNumber() throws Exception {
        BigDecimal result = calculator.evaluate("-0.003");

        assertEquals(new BigDecimal("-0.003"), result);
    }

    @Test
    public void evaluate_AddPositiveNumbers_ReturnsAddResult() throws Exception {
        BigDecimal result = calculator.evaluate("1 + 1");

        assertEquals(new BigDecimal("2"), result);
    }

    @Test
    public void evaluate_AddNegativeNumbers_ReturnsAddResult() throws Exception {
        BigDecimal result = calculator.evaluate("-10 + 1");

        assertEquals(new BigDecimal("-9"), result);
    }

    @Test
    public void evaluate_AddPositiveDecimalNumbers_ReturnsAddResult() throws Exception {
        BigDecimal result = calculator.evaluate("0.99 + 0.01");

        assertEquals(new BigDecimal("1.00"), result);
    }

    @Test
    public void evaluate_AddNegativeDecimalNumbers_ReturnsAddResult() throws Exception {
        BigDecimal result = calculator.evaluate("-0.99 + 0.01");

        assertEquals(new BigDecimal("-0.98"), result);
    }

    @Test
    public void evaluate_SubPositiveNumbers_ReturnsSubResult() throws Exception {
        BigDecimal result = calculator.evaluate("10 - 1");

        assertEquals(new BigDecimal("9"), result);
    }

    @Test
    public void evaluate_SubNegativeNumbers_ReturnsSubResult() throws Exception {
        BigDecimal result = calculator.evaluate("-10 - 1");

        assertEquals(new BigDecimal("-11"), result);
    }

    @Test
    public void evaluate_SubPositiveDecimalNumbers_ReturnsSubResult() throws Exception {
        BigDecimal result = calculator.evaluate("0.99 - 0.01");

        assertEquals(new BigDecimal("0.98"), result);
    }

    @Test
    public void evaluate_SubNegativeDecimalNumbers_ReturnsSubResult() throws Exception {
        BigDecimal result = calculator.evaluate("-0.99 - 0.01");

        assertEquals(new BigDecimal("-1.00"), result);
    }

    @Test
    public void evaluate_ComplexExpression_ReturnsComplexExpressionResult() throws Exception {
        BigDecimal result = calculator.evaluate("2*(23/(3*3))-23*(2*3)");

        assertEquals(new BigDecimal("-132.888888888"), result);
    }

    @Test
    public void evaluate_ZeroDivision_ThrowsArithmeticException() throws Exception {
        assertThrows(ArithmeticException.class, () -> calculator.evaluate("10/0"));
    }
}

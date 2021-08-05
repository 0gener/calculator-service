package com.ivansousa.calculatorservice.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Calculator {
    private int scale;
    private RoundingMode roundingMode;

    public BigDecimal evaluate(String exp) throws InvalidExpressionException {
        boolean validExpression = Pattern.matches("[ .0-9\\+\\-\\*/\\(\\)]*", exp);
        if (!validExpression || exp.equals(""))
            throw new InvalidExpressionException();

        char[] chars = exp.toCharArray();

        Stack<BigDecimal> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ')
                continue;

            if (chars[i] >= '0' && chars[i] <= '9') {
                StringBuilder sb = new StringBuilder();

                while (i < chars.length && ((chars[i] >= '0' && chars[i] <= '9') || chars[i] == '.'))
                    sb.append(chars[i++]);

                values.push(new BigDecimal(sb.toString()));
                i--;
            } else if (chars[i] == '(')
                operators.add('(');
            else if (chars[i] == ')') {
                while (operators.peek() != '(')
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));

                operators.pop();
            } else if (isOperator(chars[i])) {
                while (!operators.isEmpty() && hasPrecedence(chars[i], operators.peek()))
                    values.push(applyOperation(operators.pop(), values.pop(),
                            values.isEmpty() ? BigDecimal.ZERO : values.pop()));
                operators.push(chars[i]);
            }
        }

        while (!operators.isEmpty())
            values.push(
                    applyOperation(operators.pop(), values.pop(), values.isEmpty() ? BigDecimal.ZERO : values.pop()));

        return values.pop();
    }

    private BigDecimal applyOperation(Character operation, BigDecimal val1, BigDecimal val2) {
        switch (operation) {
            case '+':
                return val2.add(val1);
            case '-':
                return val2.subtract(val1);
            case '*':
                return val2.multiply(val1);
            case '/':
                return val2.divide(val1, scale, roundingMode);
        }

        return BigDecimal.ZERO;
    }

    private boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    private boolean hasPrecedence(char operation1, char operation2) {
        if (operation2 == '(' || operation2 == ')')
            return false;
        if ((operation1 == '*' || operation1 == '/') && (operation2 == '+' || operation2 == '-'))
            return false;
        else
            return true;
    }
}

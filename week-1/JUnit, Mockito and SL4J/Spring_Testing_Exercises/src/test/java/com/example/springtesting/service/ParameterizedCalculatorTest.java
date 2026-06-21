package com.example.springtesting.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Exercise 9: Parameterized Test with JUnit
public class ParameterizedCalculatorTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "5, 5, 10",
            "-1, 1, 0",
            "100, 200, 300"
    })
    public void testAddMultipleInputs(int a, int b, int expected) {
        int result = calculatorService.add(a, b);
        assertEquals(expected, result);
    }
}

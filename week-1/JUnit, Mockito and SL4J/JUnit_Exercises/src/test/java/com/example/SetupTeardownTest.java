package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup and Teardown
public class SetupTeardownTest {

    private Calculator calculator;

    // The @Before annotation runs before every test method, acting as Setup
    @Before
    public void setUp() {
        System.out.println("Setting up test fixture...");
        calculator = new Calculator();
    }

    // The @After annotation runs after every test method, acting as Teardown
    @After
    public void tearDown() {
        System.out.println("Tearing down test fixture...");
        calculator = null;
    }

    @Test
    public void testMultiplyWithAAA() {
        // Arrange: Initialize objects, set up dependencies, prepare inputs
        int operand1 = 4;
        int operand2 = 5;
        int expectedResult = 20;

        // Act: Execute the method being tested
        int actualResult = calculator.multiply(operand1, operand2);

        // Assert: Verify that the expected result matches the actual result
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testDivideWithAAA() {
        // Arrange
        int dividend = 20;
        int divisor = 4;
        int expectedResult = 5;

        // Act
        int actualResult = calculator.divide(dividend, divisor);

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}

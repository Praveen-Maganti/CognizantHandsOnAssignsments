package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Exercise 2: Writing Basic JUnit Tests
public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(10, 5);
        assertEquals(15, result);
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(10, 5);
        assertEquals(5, result);
    }
}

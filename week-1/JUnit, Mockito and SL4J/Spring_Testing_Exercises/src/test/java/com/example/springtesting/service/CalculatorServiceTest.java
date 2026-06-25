package com.example.springtesting.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Exercise 1: Basic Unit Test for a Service Method
public class CalculatorServiceTest {

    @Test
    public void testAdd() {
        CalculatorService service = new CalculatorService();
        int result = service.add(5, 3);
        assertEquals(8, result);
    }
}

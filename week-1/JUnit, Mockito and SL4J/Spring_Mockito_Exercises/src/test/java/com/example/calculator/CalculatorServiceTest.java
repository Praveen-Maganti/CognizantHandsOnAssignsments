package com.example.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    private final CalculatorService calculatorService = new CalculatorService();
    @Test
    void addsTwoNumbers() {
        assertEquals(5, calculatorService.add(2, 3));
        assertEquals(0, calculatorService.add(-2, 2));
        assertEquals(-7, calculatorService.add(-3, -4));
    }
}

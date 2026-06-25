package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Exercise 1: Parameterized Tests
public class EvenCheckerTest {

    private final EvenChecker checker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 100})
    void testIsEvenWithEvenNumbers(int number) {
        assertTrue(checker.isEven(number), number + " should be even");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, 99})
    void testIsEvenWithOddNumbers(int number) {
        assertFalse(checker.isEven(number), number + " should not be even");
    }
}

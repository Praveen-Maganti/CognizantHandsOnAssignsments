package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Exercise 4: Exception Testing
public class ExceptionThrowerTest {

    @Test
    void testExpectedException() {
        ExceptionThrower thrower = new ExceptionThrower();

        // Assert that the specified exception is thrown
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            thrower.throwException();
        });

        // Optionally, assert the exception message
        assertEquals("This is an expected exception", exception.getMessage());
    }
}

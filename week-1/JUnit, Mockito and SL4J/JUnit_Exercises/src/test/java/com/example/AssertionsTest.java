package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

// Exercise 3: Assertions in JUnit
public class AssertionsTest {

    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());
        
        // Assert same (checks if references point to the same object)
        String obj1 = "JUnit";
        String obj2 = "JUnit";
        assertSame(obj1, obj2);
        
        // Assert not same
        String obj3 = new String("JUnit");
        assertNotSame(obj1, obj3);
    }
}

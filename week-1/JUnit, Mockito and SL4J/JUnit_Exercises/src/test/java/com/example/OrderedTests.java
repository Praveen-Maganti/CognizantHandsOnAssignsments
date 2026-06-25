package com.example;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Exercise 3: Test Execution Order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    void firstTest() {
        System.out.println("This test runs first");
    }

    @Test
    @Order(2)
    void secondTest() {
        System.out.println("This test runs second");
    }

    @Test
    @Order(3)
    void thirdTest() {
        System.out.println("This test runs third");
    }
}

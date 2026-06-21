package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exercise 2: Parameterized Logging
public class ParameterizedLogging {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    public static void main(String[] args) {
        String user = "John Doe";
        int orderId = 12345;
        double amount = 250.50;

        // Parameterized logging is cleaner and more efficient than string concatenation
        logger.info("User '{}' has successfully placed order #{} for the amount of ${}", user, orderId, amount);
        
        // Another example
        int items = 5;
        logger.debug("Order #{} contains {} items.", orderId, items);
    }
}

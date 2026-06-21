package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exercise 3: Using Different Appenders
public class AppenderExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        // Because of logback.xml, these messages will be printed to BOTH 
        // the console and the "app.log" file.
        
        logger.debug("This is a DEBUG message, it goes to console and file.");
        logger.info("This is an INFO message, it goes to console and file.");
        logger.warn("This is a WARN message, it goes to console and file.");
        logger.error("This is an ERROR message, it goes to console and file.");
        
        System.out.println("Check the app.log file in the project root to see the logged output!");
    }
}

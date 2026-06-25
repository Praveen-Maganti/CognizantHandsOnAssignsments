package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 1: Logging Error Messages and Warning Levels
 *
 * Demonstrates how to use SLF4J to emit log messages at different severity
 * levels.  The five standard levels are (lowest → highest):
 *   TRACE < DEBUG < INFO < WARN < ERROR
 *
 * Logic is placed in instance methods so that unit tests can instantiate
 * the class, attach an in-memory ListAppender, invoke each method, and then
 * assert on the captured log events.
 */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    // -----------------------------------------------------------------------
    // Testable instance methods (called by main and by tests)
    // -----------------------------------------------------------------------

    /** Logs a WARN message about disk usage. */
    public void logWarning() {
        logger.warn("WARN: This is a warning message – disk usage above 80%");
    }

    /** Logs a simple ERROR message about a DB connection failure. */
    public void logError() {
        logger.error("ERROR: This is an error message – database connection failed");
    }

    /**
     * Triggers an ArithmeticException internally and logs it at ERROR level
     * with the exception attached so the stack trace appears in the log.
     */
    public void logErrorWithException() {
        try {
            int result = 10 / 0;   // deliberately trigger ArithmeticException
        } catch (ArithmeticException ex) {
            logger.error("ERROR: caught an unexpected arithmetic exception – {}", ex.getMessage(), ex);
        }
    }

    /** Logs messages at all five SLF4J levels. */
    public void logAllLevels() {
        logger.trace("TRACE: entering main() method");
        logger.debug("DEBUG: application has started successfully");
        logger.info ("INFO: processing user login request");
        logWarning();
        logError();
    }

    // -----------------------------------------------------------------------
    // Entry point
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        LoggingExample example = new LoggingExample();
        example.logAllLevels();
        example.logErrorWithException();
    }
}

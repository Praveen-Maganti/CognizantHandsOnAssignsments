package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Using Different Appenders
 *
 * Logback configuration (logback.xml) routes every log event to two appenders:
 *   1. ConsoleAppender  – stdout
 *   2. FileAppender     – app.log
 *
 * Instance methods expose each scenario for unit testing.
 */
public class AppenderExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    // -----------------------------------------------------------------------
    // Testable instance methods
    // -----------------------------------------------------------------------

    /** Emits one message at each level (DEBUG → ERROR). */
    public void logAllLevels() {
        logger.debug("DEBUG  → this message goes to console AND app.log");
        logger.info ("INFO   → application started, both appenders are active");
        logger.warn ("WARN   → disk space is running low (written to both appenders)");
        logger.error("ERROR  → critical failure detected (written to both appenders)");
    }

    /** Logs a structured INFO message with environment and user-count placeholders. */
    public void logEnvironmentInfo(String environment, int activeUsers) {
        logger.info("Environment: {} | Active users right now: {}", environment, activeUsers);
    }

    /**
     * Simulates a failed DB connection, logs it at ERROR, and returns the
     * exception message (for test assertions).
     */
    public String logDatabaseConnectionFailure(String url, String environment) {
        try {
            connectToDatabase(url);
        } catch (RuntimeException ex) {
            logger.error("Database connection failed in env '{}'. Cause: {}",
                    environment, ex.getMessage(), ex);
            return ex.getMessage();
        }
        return null;
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    private static void connectToDatabase(String url) {
        throw new RuntimeException("Unable to reach host at " + url);
    }

    // -----------------------------------------------------------------------
    // Entry point
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        AppenderExample ae = new AppenderExample();
        ae.logAllLevels();
        ae.logEnvironmentInfo("PRODUCTION", 312);
        ae.logDatabaseConnectionFailure("jdbc:mysql://localhost:3306/mydb", "PRODUCTION");
        System.out.println("\n✔  Check 'app.log' in the project root – it contains all messages above.");
    }
}

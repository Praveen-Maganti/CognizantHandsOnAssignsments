package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 2: Parameterized Logging
 **/
public class ParameterizedLogging {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    // -----------------------------------------------------------------------
    // Testable instance methods
    // -----------------------------------------------------------------------

    /** Single placeholder: logs a user login event. */
    public void logUserLogin(String username) {
        logger.info("User '{}' has logged in.", username);
    }

    /** Two placeholders: logs how many items a user added to cart. */
    public void logCartUpdate(String username, int quantity) {
        logger.info("User '{}' added {} item(s) to the cart.", username, quantity);
    }

    /** Multiple placeholders: logs full order details at INFO level. */
    public void logOrderConfirmation(int orderId, String username, double amount, String status) {
        logger.info("Order #{} for user '{}' – amount: ${} – status: {}",
                orderId, username, amount, status);
    }

    /** DEBUG level with placeholders: logs pagination info. */
    public void logPagination(int pageNumber, int pageSize, int totalRecords) {
        logger.debug("Fetching page {} (size {}) – {} total records found.",
                pageNumber, pageSize, totalRecords);
    }

    /** WARN level: logs suspicious failed login attempts. */
    public void logFailedLoginWarning(String username, int failedAttempts) {
        logger.warn("User '{}' has {} failed login attempts – account may be locked soon.",
                username, failedAttempts);
    }

    /**
     * ERROR level with exception: simulates a resource-not-found failure and
     * logs it with the exception attached.
     *
     * @return the message of the caught exception (used by tests to verify)
     */
    public String logResourceNotFound(String resourceId) {
        try {
            simulateResourceNotFound(resourceId);
        } catch (IllegalArgumentException ex) {
            logger.error("Failed to load resource '{}': {}", resourceId, ex.getMessage(), ex);
            return ex.getMessage();
        }
        return null;
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    private static void simulateResourceNotFound(String id) {
        throw new IllegalArgumentException("Resource with id '" + id + "' does not exist.");
    }

    // -----------------------------------------------------------------------
    // Entry point
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        ParameterizedLogging pl = new ParameterizedLogging();
        pl.logUserLogin("Alice");
        pl.logCartUpdate("Alice", 3);
        pl.logOrderConfirmation(98765, "Alice", 149.99, "CONFIRMED");
        pl.logPagination(1, 10, 47);
        pl.logFailedLoginWarning("Alice", 4);
        pl.logResourceNotFound("RES-404");
    }
}

package com.example;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 2 Tests – ParameterizedLogging
 **/
class ParameterizedLoggingTest {

    private Logger logger;
    private ListAppender<ILoggingEvent> listAppender;
    private ParameterizedLogging pl;

    @BeforeEach
    void setUp() {
        pl = new ParameterizedLogging();

        logger = (Logger) LoggerFactory.getLogger(ParameterizedLogging.class);

        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        logger.setLevel(Level.TRACE);   // capture everything
    }

    @AfterEach
    void tearDown() {
        logger.detachAppender(listAppender);
    }

    private List<ILoggingEvent> events() {
        return listAppender.list;
    }

    // Tests


    @Test
    @DisplayName("logUserLogin() – INFO level, username placeholder substituted")
    void testLogUserLogin_singlePlaceholder() {
        pl.logUserLogin("Alice");

        List<ILoggingEvent> captured = events();
        assertEquals(1, captured.size());
        assertEquals(Level.INFO, captured.get(0).getLevel());

        String msg = captured.get(0).getFormattedMessage();
        assertTrue(msg.contains("Alice"), "Formatted message must contain the username");
        assertTrue(msg.contains("has logged in"), "Formatted message must describe the event");
    }

    @Test
    @DisplayName("logUserLogin() – different username is substituted correctly")
    void testLogUserLogin_differentUser() {
        pl.logUserLogin("Bob");

        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("Bob"), "Formatted message must contain 'Bob'");
        assertFalse(msg.contains("{}"), "No unresolved '{}' placeholders should remain");
    }

    @Test
    @DisplayName("logCartUpdate() – two placeholders: username and quantity substituted")
    void testLogCartUpdate_twoPlaceholders() {
        pl.logCartUpdate("Alice", 5);

        List<ILoggingEvent> captured = events();
        assertEquals(1, captured.size());
        assertEquals(Level.INFO, captured.get(0).getLevel());

        String msg = captured.get(0).getFormattedMessage();
        assertTrue(msg.contains("Alice"),  "Must contain username");
        assertTrue(msg.contains("5"),      "Must contain quantity");
        assertFalse(msg.contains("{}"),    "No unresolved placeholders should remain");
    }

    @Test
    @DisplayName("logOrderConfirmation() – multiple placeholders (orderId, user, amount, status)")
    void testLogOrderConfirmation_multiplePlaceholders() {
        pl.logOrderConfirmation(12345, "Alice", 299.99, "CONFIRMED");

        assertEquals(1, events().size());
        String msg = events().get(0).getFormattedMessage();

        assertTrue(msg.contains("12345"),     "Must contain order ID");
        assertTrue(msg.contains("Alice"),     "Must contain username");
        assertTrue(msg.contains("299.99"),    "Must contain amount");
        assertTrue(msg.contains("CONFIRMED"), "Must contain status");
    }

    @Test
    @DisplayName("logPagination() – DEBUG level with three numeric placeholders")
    void testLogPagination_debugLevel() {
        pl.logPagination(2, 20, 100);

        assertEquals(1, events().size());
        assertEquals(Level.DEBUG, events().get(0).getLevel());

        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("2"),   "Must contain page number");
        assertTrue(msg.contains("20"),  "Must contain page size");
        assertTrue(msg.contains("100"), "Must contain total records");
    }

    @Test
    @DisplayName("logPagination() is NOT emitted when logger level is INFO")
    void testLogPagination_suppressedAboveDebug() {
        logger.setLevel(Level.INFO);  // raise threshold – DEBUG should be dropped
        pl.logPagination(1, 10, 50);
        assertTrue(events().isEmpty(), "DEBUG event must be filtered when level is INFO");
    }

    @Test
    @DisplayName("logFailedLoginWarning() – WARN level, username and attempt count substituted")
    void testLogFailedLoginWarning_warnLevel() {
        pl.logFailedLoginWarning("Alice", 4);

        assertEquals(1, events().size());
        assertEquals(Level.WARN, events().get(0).getLevel());

        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("Alice"), "Must contain username");
        assertTrue(msg.contains("4"),     "Must contain failed attempt count");
    }

    @Test
    @DisplayName("logResourceNotFound() – ERROR level, exception message substituted, Throwable attached")
    void testLogResourceNotFound_errorWithException() {
        String result = pl.logResourceNotFound("RES-404");

        // Verify the method returned the exception message
        assertNotNull(result);
        assertTrue(result.contains("RES-404"), "Returned message must reference the resource id");

        // Verify the logged event
        assertEquals(1, events().size());
        assertEquals(Level.ERROR, events().get(0).getLevel());

        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("RES-404"), "Log message must contain the resource id");

        // Verify the exception was attached to the logging event
        assertNotNull(events().get(0).getThrowableProxy(),
                "IllegalArgumentException must be attached as a Throwable");
        assertEquals("java.lang.IllegalArgumentException",
                events().get(0).getThrowableProxy().getClassName());
    }

    @Test
    @DisplayName("No '{}' placeholders remain unresolved in any formatted message")
    void testNoUnresolvedPlaceholders() {
        pl.logUserLogin("Alice");
        pl.logCartUpdate("Alice", 3);
        pl.logOrderConfirmation(1, "Alice", 10.0, "PENDING");
        pl.logPagination(1, 5, 20);
        pl.logFailedLoginWarning("Alice", 2);
        pl.logResourceNotFound("ID-001");

        for (ILoggingEvent event : events()) {
            assertFalse(event.getFormattedMessage().contains("{}"),
                    "Unresolved placeholder found in: " + event.getFormattedMessage());
        }
    }
}

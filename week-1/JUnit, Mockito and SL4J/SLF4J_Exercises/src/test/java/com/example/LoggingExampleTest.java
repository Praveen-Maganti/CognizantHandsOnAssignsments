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
 * Exercise 1 Tests – LoggingExample
 **/
class LoggingExampleTest {

    // Logback's concrete Logger (cast from SLF4J facade)
    private Logger logger;

    // In-memory appender that collects log events during a test
    private ListAppender<ILoggingEvent> listAppender;

    // The class under test
    private LoggingExample loggingExample;

    // -----------------------------------------------------------------------
    // Setup / Teardown
    // -----------------------------------------------------------------------

    @BeforeEach
    void setUp() {
        loggingExample = new LoggingExample();

        // Obtain the Logback Logger bound to LoggingExample
        logger = (Logger) LoggerFactory.getLogger(LoggingExample.class);

        // Create and start the in-memory appender
        listAppender = new ListAppender<>();
        listAppender.start();

        // Attach it to the logger
        logger.addAppender(listAppender);

        // Ensure ALL levels are captured (logback.xml sets root=DEBUG;
        // explicitly setting the level here makes tests independent of config)
        logger.setLevel(Level.TRACE);
    }

    @AfterEach
    void tearDown() {
        // Detach the appender so subsequent tests start clean
        logger.detachAppender(listAppender);
    }

    // -----------------------------------------------------------------------
    // Helper
    // -----------------------------------------------------------------------

    /** Returns the list of events captured by the in-memory appender. */
    private List<ILoggingEvent> events() {
        return listAppender.list;
    }

    // -----------------------------------------------------------------------
    // Tests
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("logWarning() emits exactly one WARN event with the expected message")
    void testLogWarning_emitsWarnEvent() {
        loggingExample.logWarning();

        List<ILoggingEvent> captured = events();
        assertEquals(1, captured.size(), "Expected exactly one log event");
        assertEquals(Level.WARN, captured.get(0).getLevel(), "Level should be WARN");
        assertTrue(captured.get(0).getFormattedMessage().contains("warning message"),
                "Message should contain 'warning message'");
    }

    @Test
    @DisplayName("logError() emits exactly one ERROR event with the expected message")
    void testLogError_emitsErrorEvent() {
        loggingExample.logError();

        List<ILoggingEvent> captured = events();
        assertEquals(1, captured.size());
        assertEquals(Level.ERROR, captured.get(0).getLevel());
        assertTrue(captured.get(0).getFormattedMessage().contains("database connection failed"),
                "Message should mention 'database connection failed'");
    }

    @Test
    @DisplayName("logErrorWithException() logs the ArithmeticException at ERROR level")
    void testLogErrorWithException_logsArithmeticException() {
        loggingExample.logErrorWithException();

        List<ILoggingEvent> captured = events();
        assertEquals(1, captured.size());
        assertEquals(Level.ERROR, captured.get(0).getLevel());

        String msg = captured.get(0).getFormattedMessage();
        assertTrue(msg.contains("arithmetic exception"),
                "Message should mention 'arithmetic exception'");
        assertTrue(msg.contains("/ by zero"),
                "Message should contain the exception detail '/ by zero'");

        // Verify the Throwable was attached to the event
        assertNotNull(captured.get(0).getThrowableProxy(),
                "A Throwable should be attached to the event");
        assertEquals("java.lang.ArithmeticException",
                captured.get(0).getThrowableProxy().getClassName());
    }

    @Test
    @DisplayName("logAllLevels() emits one event per level in the correct order")
    void testLogAllLevels_emitsFiveEvents() {
        loggingExample.logAllLevels();

        List<ILoggingEvent> captured = events();
        // Expects TRACE, DEBUG, INFO, WARN, ERROR
        assertEquals(5, captured.size(), "Expected 5 log events (one per level)");

        assertEquals(Level.TRACE, captured.get(0).getLevel());
        assertEquals(Level.DEBUG, captured.get(1).getLevel());
        assertEquals(Level.INFO,  captured.get(2).getLevel());
        assertEquals(Level.WARN,  captured.get(3).getLevel());
        assertEquals(Level.ERROR, captured.get(4).getLevel());
    }

    @Test
    @DisplayName("logWarning() is NOT emitted when logger level is ERROR")
    void testLogWarning_suppressedWhenLevelIsError() {
        // Raise the threshold so WARN messages are filtered out
        logger.setLevel(Level.ERROR);

        loggingExample.logWarning();

        assertTrue(events().isEmpty(),
                "WARN event should be filtered when logger level is ERROR");
    }

    @Test
    @DisplayName("logError() IS emitted when logger level is ERROR")
    void testLogError_notSuppressedWhenLevelIsError() {
        logger.setLevel(Level.ERROR);

        loggingExample.logError();

        assertEquals(1, events().size(),
                "ERROR event must pass through when logger level is ERROR");
    }
}

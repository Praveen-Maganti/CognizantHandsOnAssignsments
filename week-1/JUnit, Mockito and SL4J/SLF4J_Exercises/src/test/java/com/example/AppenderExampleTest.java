package com.example;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 3 Tests – AppenderExample
**/
class AppenderExampleTest {

    private Logger logger;
    private ListAppender<ILoggingEvent> listAppender;
    private AppenderExample ae;

    @BeforeEach
    void setUp() {
        ae = new AppenderExample();

        logger = (Logger) LoggerFactory.getLogger(AppenderExample.class);

        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        logger.setLevel(Level.TRACE);  // ensure all levels are captured
    }

    @AfterEach
    void tearDown() {
        logger.detachAppender(listAppender);
    }

    private List<ILoggingEvent> events() {
        return listAppender.list;
    }

    // -----------------------------------------------------------------------
    // Tests
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("logAllLevels() emits exactly four events: DEBUG, INFO, WARN, ERROR")
    void testLogAllLevels_emitsFourEvents() {
        ae.logAllLevels();

        List<ILoggingEvent> captured = events();
        assertEquals(4, captured.size(), "Expected 4 events (DEBUG, INFO, WARN, ERROR)");

        assertEquals(Level.DEBUG, captured.get(0).getLevel(), "1st event should be DEBUG");
        assertEquals(Level.INFO,  captured.get(1).getLevel(), "2nd event should be INFO");
        assertEquals(Level.WARN,  captured.get(2).getLevel(), "3rd event should be WARN");
        assertEquals(Level.ERROR, captured.get(3).getLevel(), "4th event should be ERROR");
    }

    @Test
    @DisplayName("logAllLevels() DEBUG message mentions both console and app.log")
    void testLogAllLevels_debugMessageContent() {
        ae.logAllLevels();
        String debugMsg = events().get(0).getFormattedMessage();
        assertTrue(debugMsg.contains("console") && debugMsg.contains("app.log"),
                "DEBUG message should reference both appender destinations");
    }

    @Test
    @DisplayName("logAllLevels() events would reach ConsoleAppender (level >= DEBUG)")
    void testLogAllLevels_allEventsPassDebugThreshold() {
        ae.logAllLevels();
        for (ILoggingEvent event : events()) {
            assertTrue(event.getLevel().isGreaterOrEqual(Level.DEBUG),
                    "All events must be at or above DEBUG: " + event.getLevel());
        }
    }

    @Test
    @DisplayName("logEnvironmentInfo() – INFO level, both placeholders substituted")
    void testLogEnvironmentInfo_placeholdersResolved() {
        ae.logEnvironmentInfo("STAGING", 75);

        assertEquals(1, events().size());
        assertEquals(Level.INFO, events().get(0).getLevel());

        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("STAGING"), "Must contain the environment name");
        assertTrue(msg.contains("75"),      "Must contain the active-user count");
        assertFalse(msg.contains("{}"),     "No unresolved placeholders allowed");
    }

    @Test
    @DisplayName("logEnvironmentInfo() message format contains 'Environment' and 'Active users' keys")
    void testLogEnvironmentInfo_messageStructure() {
        ae.logEnvironmentInfo("PRODUCTION", 312);
        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("Environment"),   "Must mention 'Environment' key");
        assertTrue(msg.contains("Active users"),  "Must mention 'Active users' key");
    }

    @Test
    @DisplayName("logDatabaseConnectionFailure() – ERROR level, Throwable attached, URL in message")
    void testLogDatabaseConnectionFailure_errorWithThrowable() {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String result = ae.logDatabaseConnectionFailure(url, "PRODUCTION");

        // Return value contains the exception message
        assertNotNull(result);
        assertTrue(result.contains(url), "Returned cause must reference the JDBC URL");

        assertEquals(1, events().size());
        assertEquals(Level.ERROR, events().get(0).getLevel());

        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("PRODUCTION"), "Log must contain the environment");
        assertTrue(msg.contains("Unable to reach host"), "Log must contain part of the exception message");

        assertNotNull(events().get(0).getThrowableProxy(),
                "RuntimeException must be attached as a Throwable");
        assertEquals("java.lang.RuntimeException",
                events().get(0).getThrowableProxy().getClassName());
    }

    @Test
    @DisplayName("logDatabaseConnectionFailure() with different env value is reflected in log")
    void testLogDatabaseConnectionFailure_differentEnv() {
        ae.logDatabaseConnectionFailure("jdbc:postgresql://host/db", "DEVELOPMENT");
        String msg = events().get(0).getFormattedMessage();
        assertTrue(msg.contains("DEVELOPMENT"),
                "Log message must contain the 'DEVELOPMENT' environment name");
    }

    @Test
    @DisplayName("Events above ERROR threshold are not suppressed")
    void testNoEventsFilteredAboveError() {
        logger.setLevel(Level.ERROR);   // only ERROR passes
        ae.logAllLevels();              // DEBUG, INFO, WARN, ERROR

        // Only the ERROR event should survive
        List<ILoggingEvent> captured = events();
        assertEquals(1, captured.size(), "Only the ERROR event should pass ERROR threshold");
        assertEquals(Level.ERROR, captured.get(0).getLevel());
    }

    @Test
    @DisplayName("Raising level to WARN suppresses DEBUG and INFO events from logAllLevels()")
    void testWarnThreshold_suppressesDebugAndInfo() {
        logger.setLevel(Level.WARN);
        ae.logAllLevels();

        List<ILoggingEvent> captured = events();
        assertEquals(2, captured.size(), "Only WARN and ERROR should pass a WARN threshold");

        List<Level> levels = captured.stream()
                .map(ILoggingEvent::getLevel)
                .collect(Collectors.toList());
        assertTrue(levels.contains(Level.WARN),  "WARN must be present");
        assertTrue(levels.contains(Level.ERROR), "ERROR must be present");
        assertFalse(levels.contains(Level.DEBUG), "DEBUG must be filtered");
        assertFalse(levels.contains(Level.INFO),  "INFO must be filtered");
    }
}

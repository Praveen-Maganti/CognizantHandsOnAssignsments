package com.example;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Exercise 2: Test Suites and Categories
@Suite
@SelectClasses({
    EvenCheckerTest.class,
    CalculatorTest.class,
    AssertionsTest.class,
    SetupTeardownTest.class
})
public class AllTests {
    // This class remains empty.
    // It's used only as a holder for the above annotations to define the suite.
}

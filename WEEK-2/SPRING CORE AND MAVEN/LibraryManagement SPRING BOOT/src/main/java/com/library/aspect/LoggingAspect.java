package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// Exercise 8: Define an Aspect and Advice Methods
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.*(..))")
    public void logBefore() {
        System.out.println("LoggingAspect: [BEFORE] Executing method in BookService");
    }

    @After("execution(* com.library.service.BookService.*(..))")
    public void logAfter() {
        System.out.println("LoggingAspect: [AFTER] Method execution completed in BookService");
    }
}

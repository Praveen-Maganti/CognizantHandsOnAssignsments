package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Exercise 5: Load the Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("==========================================================");
        System.out.println("Testing Annotation-based Configuration (Exercise 6)");
        System.out.println("==========================================================");
        BookService annotatedService = context.getBean("bookService", BookService.class);
        annotatedService.performService();

        System.out.println("\n==========================================================");
        System.out.println("Testing XML-based Configuration (Exercise 5 & 7 & 8)");
        System.out.println("==========================================================");
        BookService xmlService = context.getBean("xmlBookService", BookService.class);
        xmlService.performService();
    }
}

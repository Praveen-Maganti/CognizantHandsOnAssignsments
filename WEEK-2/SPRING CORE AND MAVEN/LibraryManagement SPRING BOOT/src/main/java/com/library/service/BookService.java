package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Exercise 6: Annotate with @Service
@Service
public class BookService {

    private BookRepository bookRepository;

    // Default constructor
    public BookService() {
        System.out.println("BookService: Default constructor called.");
    }

    // Exercise 7: Constructor Injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: Constructor Injection called.");
    }

    // Exercise 5 & 7: Setter Injection
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: Setter Injection called.");
    }

    public void performService() {
        System.out.println("BookService: Executing service task...");
        if (bookRepository != null) {
            bookRepository.performRepositoryTask();
        } else {
            System.out.println("BookService: BookRepository is null!");
        }
    }
}

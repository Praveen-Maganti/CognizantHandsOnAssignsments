package com.library.repository;

import org.springframework.stereotype.Repository;

// Exercise 6: Annotate with @Repository
@Repository
public class BookRepository {
    public void performRepositoryTask() {
        System.out.println("BookRepository: Executing repository task...");
    }
}

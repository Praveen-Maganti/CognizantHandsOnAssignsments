package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService{
    private BookRepository repository;

    public void setRepository(BookRepository repository){
        this.repository=repository;
    }

    public void displayBook() {
        System.out.println("Inside Book Service");
        repository.getBookDetails();
    }
}
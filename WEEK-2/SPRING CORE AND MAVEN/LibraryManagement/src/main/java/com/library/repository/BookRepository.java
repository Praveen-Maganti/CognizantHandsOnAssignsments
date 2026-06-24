package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public void getBookDetails() {
        try{
            Thread.sleep(1000);
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fetching Book Details...");
    }
}
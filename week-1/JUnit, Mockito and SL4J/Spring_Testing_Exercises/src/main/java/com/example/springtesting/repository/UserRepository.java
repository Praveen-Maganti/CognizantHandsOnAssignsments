package com.example.springtesting.repository;

import com.example.springtesting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // For Exercise 7
    List<User> findByName(String name);
}

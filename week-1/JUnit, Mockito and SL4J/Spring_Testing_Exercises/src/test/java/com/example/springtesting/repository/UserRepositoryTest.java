package com.example.springtesting.repository;

import com.example.springtesting.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // Exercise 7: Test Custom Repository Query
    @Test
    public void testFindByName() {
        userRepository.save(new User(1L, "Alice"));
        userRepository.save(new User(2L, "Bob"));
        userRepository.save(new User(3L, "Alice"));

        List<User> alices = userRepository.findByName("Alice");

        assertEquals(2, alices.size());
        assertEquals("Alice", alices.get(0).getName());
    }
}

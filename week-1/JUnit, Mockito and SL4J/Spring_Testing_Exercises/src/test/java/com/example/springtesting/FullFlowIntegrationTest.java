package com.example.springtesting;

import com.example.springtesting.model.User;
import com.example.springtesting.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Exercise 4: Integration Test with Spring Boot
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullFlowIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateAndGetUserFullFlow() {
        // Step 1: Create a user via POST
        User newUser = new User(10L, "Integration User");
        ResponseEntity<User> postResponse = restTemplate.postForEntity("/users", newUser, User.class);
        
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody());
        assertEquals("Integration User", postResponse.getBody().getName());

        // Step 2: Retrieve the user via GET
        ResponseEntity<User> getResponse = restTemplate.getForEntity("/users/10", User.class);
        
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertNotNull(getResponse.getBody());
        assertEquals("Integration User", getResponse.getBody().getName());

        // Step 3: Verify directly in the database
        User dbUser = userRepository.findById(10L).orElse(null);
        assertNotNull(dbUser);
        assertEquals("Integration User", dbUser.getName());
    }
}

package com.example.springtesting.controller;

import com.example.springtesting.model.User;
import com.example.springtesting.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // Exercise 3: Testing a REST Controller with MockMvc
    @Test
    public void testGetUser() throws Exception {
        User user = new User(1L, "John Doe");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    // Exercise 5: Test Controller POST Endpoint
    @Test
    public void testCreateUser() throws Exception {
        User user = new User(2L, "Jane Doe");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"Jane Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    // Exercise 8: Test Controller Exception Handling
    @Test
    public void testGetUserNotFoundException() throws Exception {
        when(userService.getUserById(3L)).thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(get("/users/3"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}

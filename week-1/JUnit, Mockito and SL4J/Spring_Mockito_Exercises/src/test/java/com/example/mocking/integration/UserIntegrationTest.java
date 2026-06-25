package com.example.mocking.integration;

import com.example.mocking.model.User;
import com.example.mocking.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Exercise 3: Mocking a Service Dependency in an Integration Test
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // We mock the service dependency even in the context of an integration test
    @MockBean
    private UserService userService;

    @Test
    public void testGetUserIntegration() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Integration Mock");

        // Mock the service
        when(userService.getUserById(1L)).thenReturn(mockUser);

        // Perform the full web request through the real dispatcher servlet
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Integration Mock"));
    }
}

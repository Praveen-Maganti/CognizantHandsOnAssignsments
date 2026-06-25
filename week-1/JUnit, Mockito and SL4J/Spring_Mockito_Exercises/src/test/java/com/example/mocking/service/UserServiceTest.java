package com.example.mocking.service;

import com.example.mocking.model.User;
import com.example.mocking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

// Exercise 2: Mocking a Repository in a Service Test
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Repository Mock");

        // Mock the repository dependency
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Call the service
        User result = userService.getUserById(1L);

        // Verify the result
        assertEquals(1L, result.getId());
        assertEquals("Repository Mock", result.getName());
        verify(userRepository).findById(1L);
    }
}

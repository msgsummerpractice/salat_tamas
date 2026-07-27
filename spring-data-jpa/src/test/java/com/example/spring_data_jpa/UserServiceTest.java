package com.example.spring_data_jpa;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.spring_data_jpa.model.User;
import com.example.spring_data_jpa.repository.UserRepository;
import com.example.spring_data_jpa.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .username("user1")
                .email("johndoe@example.com")
                .password("passwordJohnDoe123")
                .firstname("John")
                .lastname("Doe")
                .build();
    }

    @Test
    void testUserIsCreated() {
        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assert createdUser.getUsername().equals("user1");
        assert createdUser.getEmail().equals("johndoe@example.com");
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> users = userService.getAllUsers();
        assert users.size() == 1;
        assert users.get(0).getUsername().equals("user1");
    }

    @Test
    void testGetUserByUsernameOrEmail() {
        when(userRepository.findByUsernameOrEmail("user1", "johndoe@example.com")).thenReturn(user);
        User foundUser = userService.getUserByUsernameOrEmail("user1", "johndoe@example.com");
        assert foundUser != null;
        assert foundUser.getUsername().equals("user1");
        assert foundUser.getEmail().equals("johndoe@example.com");
    }

    @Test
    void testUsernameIsUpdated() {
        userService.updateUsername(1L, "NewUsername");
        verify(userRepository).updateUsernameById(1L, "NewUsername");
    }

    @Test
    void testDeleteUserByUsername() {
        userService.deleteUserByUsername("user1");
        verify(userRepository).deleteByUsername("user1");
    }

    @Test
    void testCountUsers() {
        when(userRepository.count()).thenReturn(1L);
        long count = userRepository.count();
        assert count == 1;
    }
}

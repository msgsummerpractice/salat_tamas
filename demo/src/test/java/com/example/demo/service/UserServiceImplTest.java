package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.InMemoryUserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    InMemoryUserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getAllUsers_returnsUsersFromRepository() {
        var users = List.of(
            new User(1L, "John Doe", "john.doe@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        var result = userService.getAllUsers();

        assertThat(result).isEqualTo(users);
    }
}
package com.example.demo.repository;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryUserRepositoryTest {
    
    private final InMemoryUserRepository repository = new InMemoryUserRepository();

    @Test
    void test_findAll_returnsExpectedUsers() {
        var users = repository.findAll();

        assertThat(users).hasSize(2);
        assertThat(users.get(0).getName()).isEqualTo("John Doe");
        assertThat(users.get(1).getEmail()).isEqualTo("jane.smith@example.com");
    }
}

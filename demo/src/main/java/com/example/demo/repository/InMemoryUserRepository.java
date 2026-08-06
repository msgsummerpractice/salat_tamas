package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {
    @Override
    public List<User> findAll() {
        return List.of(
            new User(1L, "John Doe", "john.doe@example.com"),
            new User(2L, "Jane Smith", "jane.smith@example.com")
        );
    }
} 


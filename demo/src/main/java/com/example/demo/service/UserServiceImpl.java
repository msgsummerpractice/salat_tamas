package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.InMemoryUserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final InMemoryUserRepository userRepository;

    public UserServiceImpl(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

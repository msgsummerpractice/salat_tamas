package com.example.spring_data_jpa.service;

import org.springframework.stereotype.Service;
import com.example.spring_data_jpa.repository.UserRepository;
import com.example.spring_data_jpa.model.User;

import java.util.List;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void updateUsername(Long id, String username) {
        userRepository.updateUsernameById(id, username);
    }

    public void updateEmail(Long id, String email) {
        userRepository.updateEmailById(id, email);
    }

    public void updatePassword(Long id, String password) {
        userRepository.updatePasswordById(id, password);
    }

    public void updateFirstname(Long id, String firstname) {
        userRepository.updateFirstnameById(id, firstname);
    }

    public void updateLastname(Long id, String lastname) {
        userRepository.updateLastnameById(id, lastname);
    }

    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public String getUsernameById(Long id) {
        return userRepository.getUsernameById(id);
    }

    public String getEmailById(Long id) {
        return userRepository.getEmailById(id);
    }

    public String getFirstnameById(Long id) {
        return userRepository.getFirstnameById(id);
    }

    public String getLastnameById(Long id) {
        return userRepository.getLastnameById(id);
    }
}

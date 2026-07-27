package com.example.spring_data_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.spring_data_jpa.repository.UserRepository;
import com.example.spring_data_jpa.DTO.request.UserRequest;
import com.example.spring_data_jpa.DTO.response.UserResponse;
import com.example.spring_data_jpa.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setCreatedAt(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.getById(id);
        return convertToResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(this::convertToResponse)
                .toList();
        return userResponses;
    }

    public UserResponse getUserByUsernameOrEmail(String username, String email) {
        return convertToResponse(userRepository.findByUsernameOrEmail(username, email));
    }

    public UserResponse createUser(User user) {
        return convertToResponse(userRepository.save(user));
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

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}

package com.example.spring_data_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_data_jpa.DTO.request.UpdateUserRequest;
import com.example.spring_data_jpa.DTO.request.UserRequest;
import com.example.spring_data_jpa.DTO.response.UserResponse;
import com.example.spring_data_jpa.model.User;
import com.example.spring_data_jpa.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@Valid @RequestBody UpdateUserRequest request) {
        userService.updateUsername(id, request.getUsername());
        userService.updateEmail(id, request.getEmail());
        userService.updatePassword(id, request.getPassword());
        userService.updateFirstname(id, request.getFirstname());
        userService.updateLastname(id, request.getLastname());
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("User deleted successfully").toString();
    }

    @PatchMapping("/{id}?information={information}&value={value}")
    public String updateUserInformation(@PathVariable Long id, @PathVariable String information, @PathVariable String value) {
        switch (information.toLowerCase()) {
            case "username":
                userService.updateUsername(id, value);
                break;
            case "email":
                userService.updateEmail(id, value);
                break;
            case "password":
                userService.updatePassword(id, value);
                break;
            case "firstname":
                userService.updateFirstname(id, value);
                break;
            case "lastname":
                userService.updateLastname(id, value);
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid information type").toString();
        }
        return ResponseEntity.ok().body("User information updated successfully").toString();
    }
}

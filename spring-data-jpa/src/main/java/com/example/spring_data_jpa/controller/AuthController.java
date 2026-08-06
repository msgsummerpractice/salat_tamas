package com.example.spring_data_jpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_data_jpa.DTO.request.SignInRequest;
import com.example.spring_data_jpa.DTO.request.UserRequest;
import com.example.spring_data_jpa.DTO.response.SignInResponse;
import com.example.spring_data_jpa.DTO.response.UserResponse;
import com.example.spring_data_jpa.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<SignInResponse> login(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}

package com.example.spring_data_jpa.service;

import com.example.spring_data_jpa.DTO.request.SignInRequest;
import com.example.spring_data_jpa.DTO.request.UserRequest;
import com.example.spring_data_jpa.DTO.response.SignInResponse;
import com.example.spring_data_jpa.DTO.response.UserResponse;

public interface AuthService {
    SignInResponse login(SignInRequest request);
    UserResponse register(UserRequest request);
}

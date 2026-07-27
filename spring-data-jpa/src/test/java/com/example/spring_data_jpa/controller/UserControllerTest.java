package com.example.spring_data_jpa.controller;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring_data_jpa.DTO.request.UpdateUserRequest;
import com.example.spring_data_jpa.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;
    
    @Test
    void testGetUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        String userJson = "{\"username\":\"user1\",\"email\":\"johndoe@example.com\",\"password\":\"passwordJohnDoe123\",\"firstname\":\"John\",\"lastname\":\"Doe\"}";
        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(userJson))
                .andExpect(status().isCreated());
    }

    @Test
    void testInvalidUpdateUserRequest() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest(
            "John", "Doe", "invalid-email", "user1","passwordJohnDoe123"
        );
        
        mockMvc.perform(put("/api/users/1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}

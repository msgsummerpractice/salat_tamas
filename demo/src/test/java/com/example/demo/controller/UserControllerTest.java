package com.example.demo.controller;

import com.example.demo.config.AppSettings;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    AppSettings appSettings;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(
            new UserController(userService, appSettings, "Welcome from Demo App!")
        ).build();
    }

    @Test
    void test_getAllUsers_withBlankName_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/users").param("name", "   "))
            .andExpect(status().isBadRequest());
    }

    @Test
    void test_getAllUsers_withMatchingName_returnsFilteredUsers() throws Exception {
        var users = List.of(
            new User(1L, "John Doe", "john.doe@example.com"),
            new User(2L, "Jane Smith", "jane.smith@example.com")
        );
        when(userService.getAllUsers()).thenReturn(users);
        when(appSettings.getCompanyName()).thenReturn("Demo Corp");

        mockMvc.perform(get("/users").param("name", "John Doe"))
            .andExpect(status().isOk())
            .andExpect(header().string("X-Welcome-Message", "Welcome from Demo App!"))
            .andExpect(header().string("X-Company-Name", "Demo Corp"))
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void test_getAllUsers_withUnknownName_returnsEmptyList() throws Exception {
        var users = List.of(
            new User(1L, "John Doe", "john.doe@example.com"),
            new User(2L, "Jane Smith", "jane.smith@example.com")
        );
        when(userService.getAllUsers()).thenReturn(users);
        when(appSettings.getCompanyName()).thenReturn("Demo Corp");
        
        mockMvc.perform(get("/users").param("name", "Nobody"))
            .andExpect(status().isOk())
            .andExpect(header().string("X-Welcome-Message", "Welcome from Demo App!"))
            .andExpect(header().string("X-Company-Name", "Demo Corp"))
            .andExpect(jsonPath("$.length()").value(0));
    }
}
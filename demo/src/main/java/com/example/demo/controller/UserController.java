package com.example.demo.controller;

import com.example.demo.config.AppSettings;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AppSettings appSettings;
    private final String welcomeMessage;

    public UserController(
            UserService userService,
            AppSettings appSettings,
            @Value("${app.welcome-message:Welcome from default message!}") String welcomeMessage) {
        this.userService = userService;
        this.appSettings = appSettings;
        this.welcomeMessage = welcomeMessage;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        if (name != null && name.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        var users = userService.getAllUsers();

        if (name != null) {
            users = users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .toList();
        }

        return ResponseEntity.ok()
            .header("X-Welcome-Message", welcomeMessage)
            .header("X-Company-Name", appSettings.getCompanyName())
            .body(users);
    }
}
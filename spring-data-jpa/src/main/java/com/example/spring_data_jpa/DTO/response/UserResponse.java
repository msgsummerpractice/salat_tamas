package com.example.spring_data_jpa.DTO.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDateTime createdAt;

    public UserResponse(Long id, String username, String email, String firstname, String lastname, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.createdAt = createdAt;
    }
}

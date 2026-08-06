package com.example.spring_data_jpa.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Size(min = 5, max=50, message = "Email must be at least 5 characters long")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}

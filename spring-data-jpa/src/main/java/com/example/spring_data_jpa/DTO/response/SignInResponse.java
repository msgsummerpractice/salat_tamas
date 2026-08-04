package com.example.spring_data_jpa.DTO.response;

import java.util.Set;

import com.example.spring_data_jpa.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    private String token;
    private Set<Role> roles;
}

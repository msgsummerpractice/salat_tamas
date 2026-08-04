package com.example.spring_data_jpa.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.spring_data_jpa.DTO.request.SignInRequest;
import com.example.spring_data_jpa.DTO.request.UserRequest;
import com.example.spring_data_jpa.DTO.response.SignInResponse;
import com.example.spring_data_jpa.DTO.response.UserResponse;
import com.example.spring_data_jpa.config.SecurityConfig;
import com.example.spring_data_jpa.model.Role;
import com.example.spring_data_jpa.model.User;
import com.example.spring_data_jpa.repository.RoleRepository;
import com.example.spring_data_jpa.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SecurityConfig securityConfig;
    private final UserService userService;

    
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, SecurityConfig securityConfig, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.securityConfig = securityConfig;
        this.userService = userService;
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public SignInResponse login(SignInRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByUsernameOrEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roles = user.getRoles();

        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();

        return new SignInResponse(token, roles);
    }

    @Override
    public UserResponse register(UserRequest request) {
        if(userRepository.findByUsernameOrEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email or Username is already in use");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(securityConfig.passwordEncoder().encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();

        Role userRole = roleRepository.findByName(Role.Name.USER)
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.getRoles().add(userRole);
        User savedUser = userRepository.save(user);

        return userService.convertToResponse(savedUser);
    }
    
}

package com.example.spring_data_jpa.DTO.request;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class UpdateUserRequestTest {
    
    private Validator validator;
    
    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testValidUpdateUserRequest() {
        UpdateUserRequest request = new UpdateUserRequest(
                "John", "Doe", "johndoe@example.com", "user1","passwordJohnDoe123"
        );
        Set<ConstraintViolation<UpdateUserRequest>> violations = validator.validate(request);
        assert violations.isEmpty();
    }

    @Test
    void testInvalidEmailThrowsValidationError() {
        UpdateUserRequest request = new UpdateUserRequest(
                "John", "Doe", "invalid-email", "user1","passwordJohnDoe123"
        );
        Set<ConstraintViolation<UpdateUserRequest>> violations = validator.validate(request);
        assert !violations.isEmpty();
    }

    @Test
    void testPasswordTooShortThrowsValidationError() {
        UpdateUserRequest request = new UpdateUserRequest(
                "John", "Doe", "johndoe@example.com", "user1","short"
        );
        Set<ConstraintViolation<UpdateUserRequest>> violations = validator.validate(request);
        assert !violations.isEmpty();
    }
}

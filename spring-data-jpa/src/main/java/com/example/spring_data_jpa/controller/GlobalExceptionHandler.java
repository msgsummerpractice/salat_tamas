package com.example.spring_data_jpa.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring_data_jpa.DTO.response.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthenticationException(
            AuthenticationException ex, HttpServletRequest request) {
        ApiErrorResponse response = ApiErrorResponse.of(
                HttpStatus.UNAUTHORIZED, "Invalid credentials", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
        
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, 
            HttpServletRequest request) 
    {
                
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (first, second) -> first,
                        LinkedHashMap::new));
        
        ApiErrorResponse errorResponse = ApiErrorResponse.of(
            HttpStatus.BAD_REQUEST, "Validation failed", request.getRequestURI());
        errorResponse.setValidationErrors(fieldErrors);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleResponseStatus(
        ResponseStatusException ex, HttpServletRequest request) 
    {
    
            HttpStatusCode code = ex.getStatusCode();
            HttpStatus status = (code instanceof HttpStatus) ? (HttpStatus) code : HttpStatus.INTERNAL_SERVER_ERROR;
    
            ApiErrorResponse response = ApiErrorResponse.of(
                    status,
                    ex.getReason() != null ? ex.getReason() : "Request failed",
                    request.getRequestURI()
            );
    
            return ResponseEntity.status(status).body(response);
    }
            
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        ApiErrorResponse response = ApiErrorResponse.of(
                HttpStatus.CONFLICT,
                "Database constraint violation",
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request) {
        log.error("Unhandled exception at {}", request.getRequestURI(), ex);
        ApiErrorResponse response = ApiErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected server error",
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
        public ResponseEntity<ApiErrorResponse> handleAuthorizationDenied(
                AuthorizationDeniedException ex,
                HttpServletRequest request) {
                
            ApiErrorResponse response = ApiErrorResponse.of(
                    HttpStatus.FORBIDDEN,
                    "Access denied",
                    request.getRequestURI());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ApiErrorResponse> handleAccessDenied(
                AccessDeniedException ex,
                HttpServletRequest request) {
                
            ApiErrorResponse response = ApiErrorResponse.of(
                    HttpStatus.FORBIDDEN,
                    "Access denied",
                    request.getRequestURI());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
}


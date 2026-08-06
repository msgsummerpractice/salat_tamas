package com.example.spring_data_jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_data_jpa.model.Role;
import com.example.spring_data_jpa.model.Role.Name;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Name name);
    
}

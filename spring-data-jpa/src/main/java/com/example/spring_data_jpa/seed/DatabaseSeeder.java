package com.example.spring_data_jpa.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.spring_data_jpa.factory.UserFactory;
import com.example.spring_data_jpa.model.Role;
import com.example.spring_data_jpa.model.User;
import com.example.spring_data_jpa.repository.RoleRepository;
import com.example.spring_data_jpa.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DatabaseSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        seedRoles();
        seedUsers();
        seedAdmin();
    }

    private void seedRoles() {
        if(roleRepository.findByName(Role.Name.USER).isEmpty()) {
            roleRepository.save(Role.builder().name(Role.Name.USER).build());
        }
        if(roleRepository.findByName(Role.Name.ADMIN).isEmpty()) {
            roleRepository.save(Role.builder().name(Role.Name.ADMIN).build());
        }
    }

    private void seedUsers() {
        if(userRepository.count() > 0) return;

        Role userRole = roleRepository.findByName(Role.Name.USER).orElseThrow(() -> new RuntimeException("User role not found"));

        List<User> users = UserFactory.createMany(30);
        users.forEach(u -> u.setRoles(Set.of(userRole)));
        
        userRepository.saveAll(users);
        System.out.println("DatabaseSeeder: Seeded with 30 users.");
    }

    private void seedAdmin() {
        if (userRepository.findByUsername("admin").isPresent()) return;

        Role adminRole = roleRepository.findByName(Role.Name.ADMIN)
            .orElseThrow(() -> new IllegalStateException("ADMIN role missing"));

        User admin = User.builder()
            .username("admin")
            .email("admin@example.com")
            .password("adminPassword123")
            .firstname("System")
            .lastname("Admin")
            .createdAt(LocalDateTime.now())
            .roles(Set.of(adminRole))
            .build();

        userRepository.save(admin);
        System.out.println("Admin account seeded.");
    }
}

package com.example.spring_data_jpa.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.spring_data_jpa.factory.UserFactory;
import com.example.spring_data_jpa.repository.UserRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if(userRepository.count() == 0) {
            userRepository.saveAll(UserFactory.createMany(30));
            System.out.println("Database seeded with 30 users.");
        }
    }
}

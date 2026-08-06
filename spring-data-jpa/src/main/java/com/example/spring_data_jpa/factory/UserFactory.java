package com.example.spring_data_jpa.factory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import com.example.spring_data_jpa.model.User;

public class UserFactory {

    public static User create() {
        Long genNumber = ThreadLocalRandom.current().nextLong(1, 9999999999L);
        String username = "genUser_" + genNumber;
        String email = username + "@generated.com";
        String password = "generatedPassword_" + genNumber;
        String firstname = "generatedFirstname_" + genNumber;
        String lastname = "generatedLastname_" + genNumber;
        LocalDateTime createdAt = LocalDateTime.now();
        User genUser = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .firstname(firstname)
                .lastname(lastname)
                .createdAt(createdAt)
                .build();

        return genUser;
    }
    
    public static List<User> createMany(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> create())
                .toList();
    }
}

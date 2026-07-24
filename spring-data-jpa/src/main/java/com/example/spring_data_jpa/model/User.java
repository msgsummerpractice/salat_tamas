package com.example.spring_data_jpa.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class User {
    
    @Setter(AccessLevel.NONE)
    private String id;

    private String username;

    @EqualsAndHashCode.Include
    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Include
    private String password;

    private String firstname;

    private String lastname;
}

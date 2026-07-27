package com.example.spring_data_jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class User {
    
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 50)
    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 30)
    private String firstname;

    @Column(nullable = false, length = 30)
    private String lastname;
}

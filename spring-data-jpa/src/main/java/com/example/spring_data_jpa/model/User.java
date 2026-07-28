package com.example.spring_data_jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    @PreUpdate
    private void hashPassword() {
        if (this.password != null && !this.password.startsWith("$2a$") && !this.password.startsWith("$2b$") && !this.password.startsWith("$2y$")) {
            this.password = new BCryptPasswordEncoder().encode(this.password);
        }
    }
}

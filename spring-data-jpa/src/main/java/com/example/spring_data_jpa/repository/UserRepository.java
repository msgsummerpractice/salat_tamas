package com.example.spring_data_jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring_data_jpa.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User getById(Long id);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :login OR u.email = :login")
    Optional<User> findByUsernameOrEmail(@Param("login") String login);
        
    void deleteByUsername(String username);

    void deleteByEmail(String email);

    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.username = :username WHERE u.id = :id")
    void updateUsernameById(@Param("id") Long id, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    void updateEmailById(@Param("id") Long id, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(@Param("id") Long id, @Param("password") String password);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstname = :firstname WHERE u.id = :id")
    void updateFirstnameById(@Param("id") Long id, @Param("firstname") String firstname);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.lastname = :lastname WHERE u.id = :id")
    void updateLastnameById(@Param("id") Long id, @Param("lastname") String lastname);

    @Query("SELECT u.username FROM User u WHERE u.id = :id")
    String getUsernameById(@Param("id") Long id);

    @Query("SELECT u.email FROM User u WHERE u.id = :id")
    String getEmailById(@Param("id") Long id);

    @Query("SELECT u.firstname FROM User u WHERE u.id = :id")
    String getFirstnameById(@Param("id") Long id);

    @Query("SELECT u.lastname FROM User u WHERE u.id = :id")
    String getLastnameById(@Param("id") Long id);

    @Query("SELECT COUNT(u) FROM User u")
    Long countUsers();
}

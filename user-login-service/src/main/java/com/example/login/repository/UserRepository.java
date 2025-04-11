package com.example.login.repository;

import com.example.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Method needed by UserDetailsService to find user by username
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username); // Good to have
}
package com.example.userregistrationservice.repository;

import com.example.userregistrationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Method to check if a user exists by username
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username); // Needed later for login
}
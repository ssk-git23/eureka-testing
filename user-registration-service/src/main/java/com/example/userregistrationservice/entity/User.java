package com.example.userregistrationservice.entity; // Or com.example.login.entity in the other service

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects; // Import for equals/hashCode helper

// REMOVE Lombok imports:
// import lombok.Data;
// import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
// REMOVE Lombok annotations:
// @Data
// @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    // --- Manually Add Code Below ---

    // 1. No-Argument Constructor (Required by JPA)
    public User() {
    }

    // 2. Optional: All-Arguments Constructor (Convenient for creating instances)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 3. Getters and Setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 4. equals() method (Good practice for entities, often based on unique business key or ID)
    // Here, using 'username' as it's unique and available before ID is generated.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        // Use Objects.equals to handle nulls gracefully, though username shouldn't be null here
        return Objects.equals(username, user.username);
    }

    // 5. hashCode() method (Must be consistent with equals)
    @Override
    public int hashCode() {
        // Use Objects.hash for a good hash code implementation
        return Objects.hash(username);
    }

    // 6. toString() method (Useful for logging/debugging - **DO NOT INCLUDE PASSWORD**)
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               // DO NOT include password field here for security!
               '}';
    }
}
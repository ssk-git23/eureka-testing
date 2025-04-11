package com.example.login.entity; // Make sure this package declaration is correct for your project structure

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects; // Required for Objects.hash and Objects.equals

/**
 * Represents a user entity in the database.
 * This version does NOT use Lombok.
 */
@Entity
@Table(name = "users") // Maps this class to the 'users' table in the database
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID (suitable for PostgreSQL SERIAL/BIGSERIAL)
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // Database constraints for the username column
    private String username;

    @Column(nullable = false) // Database constraints for the password column (length can be adjusted, BCrypt is usually 60 chars)
    private String password;

    // --- Boilerplate Code (Replaces Lombok annotations) ---

    /**
     * Default no-argument constructor required by JPA.
     */
    public User() {
    }

    /**
     * Optional constructor for creating users with initial data.
     * @param username The username.
     * @param password The password (should be hashed before calling this directly, typically service handles hashing).
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // --- Getters ---

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // --- Setters ---

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // --- equals() and hashCode() ---
    // Important for entity management, especially in collections.
    // Often based on a unique business key (like username) or the ID after it's assigned.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Use getClass() comparison for JPA entities to handle proxies correctly
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        // Use username for equality check as it's defined as unique and non-null
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        // Base hashCode on the same field(s) used in equals()
        return Objects.hash(username);
    }

    // --- toString() ---
    // Useful for logging, BUT EXCLUDE SENSITIVE DATA like passwords.

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               // IMPORTANT: Do NOT include the password field in toString() for security!
               '}';
    }
}
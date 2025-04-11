package com.example.userregistrationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Minimal security config just to get PasswordEncoder
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Allow access to registration endpoints without Spring Security intercepting
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in this example
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/registration-success", "/css/**", "/js/**").permitAll() // Allow access to registration page and static resources
                .anyRequest().authenticated() // Secure other endpoints if any exist (none defined here yet)
            )
            .formLogin(form -> form.disable()); // Disable form login for this service

        return http.build();
    }
}
package com.example.login.config;

import com.example.login.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher; // For logout

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(); // Provide our custom UserDetailsService
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Must use the same encoder as registration service
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/css/**", "/js/**", "/error").permitAll() // Allow access to login page, static resources, and error page
                .requestMatchers("/register-link").permitAll() // Allow access to link to registration
                .anyRequest().authenticated() // All other requests require authentication
            )
            .formLogin(form -> form
                .loginPage("/login") // Specify custom login page URL
                .loginProcessingUrl("/login") // URL where login form POSTs (handled by Spring Security)
                .defaultSuccessUrl("/welcome", true) // Redirect to /welcome on successful login
                .failureUrl("/login?error=true") // Redirect back to login on failure
                .permitAll() // Allow everyone to access the login page URL itself
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL to trigger logout
                .logoutSuccessUrl("/login?logout=true") // Redirect to login page after logout
                .invalidateHttpSession(true) // Invalidate session
                .deleteCookies("JSESSIONID") // Delete session cookie
                .permitAll()
            );
         // Authentication provider setup is implicitly done via the DaoAuthenticationProvider Bean
        // CSRF is enabled by default, Thymeleaf integration handles the token automatically in forms

        return http.build();
    }
}
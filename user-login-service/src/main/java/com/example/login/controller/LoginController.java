package com.example.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been logged out successfully.");
        }
        return "login"; // Return login.html
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "User"; // Default
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication != null) {
             username = authentication.getName();
        }

        model.addAttribute("username", username);
        return "welcome"; // Return welcome.html
    }

     // Optional: Endpoint to provide a clear link to the registration service
    @GetMapping("/register-link")
    public String redirectToRegister() {
        // Redirects the browser to the registration service URL
        return "redirect:http://localhost:8081/register";
    }
}
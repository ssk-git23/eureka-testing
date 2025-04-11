package com.example.userregistrationservice.controller;

import com.example.userregistrationservice.entity.User;
import com.example.userregistrationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Add an empty user object to bind form data
        if (!model.containsAttribute("user")) { // Avoid overwriting flash attribute
             model.addAttribute("user", new User());
        }
        return "register"; // Return the name of the Thymeleaf template (register.html)
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
            // Redirect to login page (on the other service) - simplest way is client-side redirect or showing success here
            // For now, redirecting back to registration page with success message
             return "redirect:/registration-success";
            // Or potentially: return "redirect:http://localhost:8080/login"; // Hardcoded URL - not ideal
        } catch (Exception e) {
            // Add user back to model to repopulate form, add error message
            redirectAttributes.addFlashAttribute("user", user); // Send back the entered data
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "redirect:/register"; // Redirect back to the registration form
        }
    }

    @GetMapping("/registration-success")
    public String registrationSuccess() {
        return "registration-success"; // A simple success page
    }
}
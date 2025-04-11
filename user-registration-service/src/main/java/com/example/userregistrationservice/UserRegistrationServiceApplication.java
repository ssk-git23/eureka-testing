package com.example.userregistrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // Generic discovery client

@SpringBootApplication
@EnableDiscoveryClient // Enable registration with Eureka (or other discovery services)
public class UserRegistrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationServiceApplication.class, args);
    }
}
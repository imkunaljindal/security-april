package com.example.securityapril.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Application";
    }

    @GetMapping("/student")
    @PreAuthorize("hasAuthority('ROLE_STUDENT') or hasAuthority('ROLE_ADMIN')")
    public String welcomeStudent() {
        return "Welcome Student";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String welcomeAdmin() {
        return "Welcome Admin";
    }
}

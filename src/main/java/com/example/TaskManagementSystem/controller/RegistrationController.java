package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.DTOs.RegistrationDTO;
import com.example.TaskManagementSystem.model.Role;
import com.example.TaskManagementSystem.model.UserEntity;
import com.example.TaskManagementSystem.service.impl.RoleServiceImpl;
import com.example.TaskManagementSystem.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public RegistrationController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        // Validate the registrationDTO and perform necessary checks

        if (registrationDTO.getUsername() == null || registrationDTO.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required.");
        }

        if (registrationDTO.getPassword() == null || registrationDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required.");
        }

        // Check if the username already exists
        if (userService.findByUsername(registrationDTO.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        // Create a new User instance
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDTO.getUsername());
        userEntity.setPassword(registrationDTO.getPassword());

        // Set the default role for the user
        Role defaultRole = roleService.findByName("ROLE_USER");
        userEntity.getRoles().add(defaultRole);

        // Save the user
        userService.save(userEntity);

        return ResponseEntity.ok("User registered successfully!");
    }
}

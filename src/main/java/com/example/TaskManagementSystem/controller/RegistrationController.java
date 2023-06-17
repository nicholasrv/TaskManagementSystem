package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.DTOs.RegistrationDTO;
import com.example.TaskManagementSystem.model.Role;
import com.example.TaskManagementSystem.model.User;
import com.example.TaskManagementSystem.service.RoleService;
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

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        // Validate the registrationDTO and perform necessary checks

        // Create a new User instance
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());

        // Set the default role for the user
        Role defaultRole = roleService.findByName("ROLE_USER");
        user.getRoles().add(defaultRole);

        // Save the user
        userService.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}

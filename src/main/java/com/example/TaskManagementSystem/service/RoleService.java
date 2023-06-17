package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.model.Role;

public interface RoleService {
    Role findByName(String name);
}


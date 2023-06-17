package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.model.Privilege;

public interface PrivilegeService {
    Privilege findByName(String name);
}

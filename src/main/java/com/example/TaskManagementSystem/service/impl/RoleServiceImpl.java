package com.example.TaskManagementSystem.service.impl;

import com.example.TaskManagementSystem.model.Role;
import com.example.TaskManagementSystem.repository.RoleRepository;
import com.example.TaskManagementSystem.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
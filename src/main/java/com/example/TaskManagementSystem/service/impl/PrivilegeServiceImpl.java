package com.example.TaskManagementSystem.service.impl;

import com.example.TaskManagementSystem.model.Privilege;
import com.example.TaskManagementSystem.model.Role;
import com.example.TaskManagementSystem.repository.PrivilegeRepository;
import com.example.TaskManagementSystem.repository.RoleRepository;
import com.example.TaskManagementSystem.service.PrivilegeService;
import com.example.TaskManagementSystem.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Privilege findByName(String name) {
        return privilegeRepository.findByName(name);
    }
}

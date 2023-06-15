package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}

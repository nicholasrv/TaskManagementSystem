package com.example.TaskManagementSystem.repository;


import com.example.TaskManagementSystem.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    public Privilege findByName(String name);
}

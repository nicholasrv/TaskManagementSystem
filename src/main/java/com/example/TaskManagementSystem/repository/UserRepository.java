package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByFirstName(String firstName);
    public User findByEmail(String email);
}

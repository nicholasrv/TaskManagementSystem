package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
}

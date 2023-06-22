package com.example.TaskManagementSystem.DTOs;

import com.example.TaskManagementSystem.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TasksResponseDTO {

    private String title;
    private String description;

    private LocalDate dueDate;
    private String status;
    private UserEntity userEntity;

    public TasksResponseDTO(String title, String description, LocalDate dueDate, String status, UserDTO userDTO) {
    }

}

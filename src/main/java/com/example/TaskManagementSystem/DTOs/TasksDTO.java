package com.example.TaskManagementSystem.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TasksDTO {

    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private String status;

    private Long idUser;

}

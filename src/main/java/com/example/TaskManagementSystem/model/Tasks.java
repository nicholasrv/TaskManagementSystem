package com.example.TaskManagementSystem.model;

import com.example.TaskManagementSystem.DTOs.TasksResponseDTO;
import com.example.TaskManagementSystem.DTOs.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Tasks")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String title;
    public String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate dueDate;
    public String status;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;

    public Tasks(String title, String description, LocalDate dueDate, String status, UserEntity userEntity) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.userEntity = userEntity;
    }

    public TasksResponseDTO responseDTO(){

        UserDTO userDTO = new UserDTO(this.userEntity.getUsername());

        return new TasksResponseDTO(
                this.title,
                this.description,
                this.dueDate,
                this.status,
                userDTO
        );
    }
}

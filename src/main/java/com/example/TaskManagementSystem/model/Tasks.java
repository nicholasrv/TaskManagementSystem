package com.example.TaskManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

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
    private User user;

}

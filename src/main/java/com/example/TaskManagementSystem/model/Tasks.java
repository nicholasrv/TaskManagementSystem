package com.example.TaskManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Users")

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
    public LocalDate dueDate;
    public String status;

    @ManyToOne
    private User user;

}

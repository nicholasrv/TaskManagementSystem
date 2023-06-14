package com.example.TaskManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String username;
    public String email;
    public String password;

}

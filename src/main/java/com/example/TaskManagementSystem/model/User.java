package com.example.TaskManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String password;

    public boolean enabled;
    public boolean tokenExpired;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="user")
    private List<Tasks> tasks;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;



}

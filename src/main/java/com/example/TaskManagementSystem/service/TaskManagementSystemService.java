package com.example.TaskManagementSystem.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TaskManagementSystemService <T>{
    public T save (T t);

    public String update (T t);

    public List<T> getAllResults();

    public Optional<T> searchById(Long id);

    public boolean delete(Long id);

}

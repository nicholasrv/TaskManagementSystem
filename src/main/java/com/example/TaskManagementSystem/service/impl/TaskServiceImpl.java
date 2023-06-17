package com.example.TaskManagementSystem.service.impl;

import com.example.TaskManagementSystem.model.Tasks;
import com.example.TaskManagementSystem.repository.TasksRepository;
import com.example.TaskManagementSystem.service.TaskManagementSystemService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskManagementSystemService<Tasks> {

    private final TasksRepository tasksRepository;

    public TaskServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Tasks save(Tasks tasks) {
        if(tasks != null){
            return tasksRepository.save(tasks);
        }
        return new Tasks();
    }

    @Override
    public String update(Tasks tasks) {
        if (tasks != null && tasksRepository.findById(tasks.getId()).isPresent()){
            tasksRepository.saveAndFlush(tasks);
            return "The requested task was successfully updated!";
        }
        return "Sorry, but the requested task couldn't be found";
    }

    @Override
    public List<Tasks> getAllResults() throws SQLException {
        return tasksRepository.findAll();
    }

    @Override
    public Optional<Tasks> searchById(Long id) throws SQLException {
        return tasksRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(tasksRepository.findById(id).isPresent()){
            tasksRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

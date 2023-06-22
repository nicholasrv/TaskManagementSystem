package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.DTOs.TasksDTO;
import com.example.TaskManagementSystem.DTOs.TasksResponseDTO;
import com.example.TaskManagementSystem.exceptions.BadRequestException;
import com.example.TaskManagementSystem.exceptions.ResourceNotFoundException;
import com.example.TaskManagementSystem.model.Tasks;
import com.example.TaskManagementSystem.model.UserEntity;
import com.example.TaskManagementSystem.service.impl.TaskServiceImpl;
import com.example.TaskManagementSystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private final TaskServiceImpl taskService;

    private final UserServiceImpl userService;

    public TaskController(TaskServiceImpl taskService, UserServiceImpl userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @PostMapping("/tasks/new")
    public TasksResponseDTO createTask(@RequestBody TasksDTO tasksDTO) throws SQLException {

        /// Checks if the task already exists and if it's already pending
        if(taskService.existsTasksByTitle(tasksDTO.getTitle()) && tasksDTO.getStatus().equals("pending")){
            throw new BadRequestException("This task was already registered and it's currently pending!");
        }

        /// Searches for the user and instantiates it
        UserEntity user = userService.searchById(tasksDTO.getIdUser()).orElse(null);

        // Creates the task by calling all getters from the DTO and the requested user.
        Tasks tasks = new Tasks(tasksDTO.getTitle(), tasksDTO.getDescription(), tasksDTO.getDueDate(), tasksDTO.getStatus(), user);

        taskService.save(tasks);

        return tasks.responseDTO();
    }


    ///UPDATE/PUT
    @PutMapping("/tasks/update")
    public ResponseEntity updateTask(@RequestBody Tasks tasks) throws SQLException {
        return ResponseEntity.ok(taskService.update(tasks));
    }

    // GET
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = "application/json")
    public List<Tasks> getAllTasks() throws SQLException{
        return taskService.getAllResults();
    }


    // DELETE
    @DeleteMapping("/tasks/delete/{id}")
    public ResponseEntity deleteTasks(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = taskService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected task has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The task with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Optional<Tasks>> getTaskById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Tasks> tasks = taskService.searchById(id);
            if(tasks.isPresent()){
                return ResponseEntity.ok(tasks);
            }
            throw new ResourceNotFoundException("The task with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching task with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }
}



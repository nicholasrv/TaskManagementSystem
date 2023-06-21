package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.exceptions.BadRequestException;
import com.example.TaskManagementSystem.exceptions.ResourceNotFoundException;
import com.example.TaskManagementSystem.model.Tasks;
import com.example.TaskManagementSystem.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    //POST
    @PostMapping("/tasks/save")
    public ResponseEntity<?> saveNewTask(@RequestBody Tasks tasks) throws BadRequestException {
        try {
            boolean doesThisTaskExist = taskService.existsTasksByTitle(tasks.getTitle());
            if (doesThisTaskExist){
                return ResponseEntity.badRequest().body("This task already exists on the database.");
            }
            return ResponseEntity.ok("Task saved successfully!" + taskService.save(tasks));
        } catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("An error has occurred while trying to save this task. Please contact our support team for further information.");
        }
    }

    ///UPDATE/PUT
    @PutMapping("/tasks/update")
    public ResponseEntity updateTask(@RequestBody Tasks tasks) throws SQLException {
        return ResponseEntity.ok(taskService.update(tasks));
    }

    // GET
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = "application/json")
    public List<Tasks> getAllDogs() throws SQLException{
        return taskService.getAllResults();
    }


    // DELETE
    @DeleteMapping("/tasks/delete/{id}")
    public ResponseEntity deleteDog(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
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



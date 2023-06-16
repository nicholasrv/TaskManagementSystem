package com.example.TaskManagementSystem.service.impl;

import com.example.TaskManagementSystem.model.User;
import com.example.TaskManagementSystem.repository.UserRepository;
import com.example.TaskManagementSystem.service.TaskManagementSystemService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements TaskManagementSystemService<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if(user != null){
            return userRepository.save(user);
        }
        return new User();
    }

    @Override
    public String update(User user) {
        if (user != null && userRepository.findById(user.getId()).isPresent()){
            userRepository.saveAndFlush(user);
            return "The requested user was successfully updated!";
        }
        return "Sorry, but the requested user couldn't be found";
    }

    @Override
    public List<User> getAllResults() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> searchById(Long id) throws SQLException {
        return userRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

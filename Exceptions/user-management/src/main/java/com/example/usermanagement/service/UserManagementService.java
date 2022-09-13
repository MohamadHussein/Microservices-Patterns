package com.example.usermanagement.service;

import com.example.usermanagement.adapter.repository.UserRepository;
import com.example.usermanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementService {

    private final UserRepository userRepository;

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getById(String id){
        return userRepository.getById(id);
    }

    public Optional<User> updateUser(User user){
        return userRepository.update(user);
    }

    public Optional<User> deleteUser(String id){

        return Optional.ofNullable(userRepository.remove(id));

    }
    public boolean exists(String id){
        return userRepository.hasUser(id);
    }
}

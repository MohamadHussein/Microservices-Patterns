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

    public Optional<User> getById(String id){
        return userRepository.getById(id);
    }
}

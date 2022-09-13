package com.example.usermanagement.service;

import com.example.usermanagement.adapter.repository.UserRepository;
import com.example.usermanagement.model.User;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserManagementService {

    private final UserRepository userRepository;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public UserManagementService(UserRepository userRepository, CircuitBreakerFactory circuitBreakerFactory) {
        this.userRepository = userRepository;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public Optional<User> getById(String id){
        System.out.println("id = " + id);
        boolean res = circuitBreakerFactory.create("validationCB").run(()->validateId(id));
        if (res) {
            return userRepository.getById(id);
        }
        return Optional.empty();
    }
    public boolean validateId(String id){
        Integer val = Integer.valueOf(id);
        if(val>=0 && val < Integer.MAX_VALUE)return true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}

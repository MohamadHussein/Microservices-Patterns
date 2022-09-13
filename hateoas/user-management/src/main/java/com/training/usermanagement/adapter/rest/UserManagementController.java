package com.example.usermanagement.adapter.rest;

import com.example.usermanagement.adapter.rest.dto.UserDTO;
import reactive.src.main.java.com.example.usermanagement.exceptions.DuplicateUserException;
import com.example.usermanagement.exceptions.UserNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserManagementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


import java.util.function.Function;

@RestController
@RequestMapping( "/users")
@RefreshScope

public class UserManagementController {
    private  final UserManagementService userManagementService;
    @Value("${com.test.port:8787}")
    private int testt;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO){
        if(userManagementService.exists(userDTO.getId())) throw new DuplicateUserException();
        return userManagementService.createUser(toModel().apply(userDTO));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserDTO userDTO){
        return userManagementService.updateUser(toModel().apply(userDTO))
                .orElseThrow(()-> new UserNotFoundException());
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id){

        return userManagementService.deleteUser(id)
                .orElseThrow(()->new UserNotFoundException());
    }


    @GetMapping("/{id}")
    public User userById(@PathVariable String id){
        System.out.println("port = " + testt);
        return userManagementService.getById(id)
                .orElseThrow(()->new UserNotFoundException());
    }


    private Function<UserDTO, User> toModel() {
        return userDTO -> User.builder()
                .id(userDTO.getId())
                .userName(userDTO.getUserName())
                .birthdate(userDTO.getBirthdate())
                .build();
    }
}

package com.example.usermanagement.adapter.rest;

import com.example.usermanagement.adapter.rest.dto.UserDTO;
import reactive.src.main.java.com.example.usermanagement.exceptions.DuplicateUserException;
import com.example.usermanagement.exceptions.UserNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserManagementService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.function.Function;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping( "/users")
public class UserManagementController {
    private  final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    @PostMapping
    public ResponseEntity<EntityModel<User>>  createUser(@RequestBody UserDTO userDTO){
        if(userManagementService.exists(userDTO.getId())) throw new DuplicateUserException();
        User user = toModel().apply(userDTO);
        userManagementService.createUser(user);

        return ResponseEntity.ok(EntityModel.of(user)
                .add(
                        linkTo(methodOn(UserManagementController.class)
                                .updateUser(user.getId(), userDTO))
                                .withRel("update"))
                .add(linkTo(methodOn(UserManagementController.class)
                        .deleteUser(userDTO.getId()))
                        .withRel("delete"))
                .add(linkTo(methodOn(UserManagementController.class)
                        .userById(userDTO.getId())).withRel("getById")));
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


    @GetMapping
    public CollectionModel<User> allUsers(){
        return CollectionModel.of(userManagementService.getAllUser());

    }
    @GetMapping("/{id}")
    public User userById(@PathVariable String id){
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

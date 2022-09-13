package com.example.usermanagement.adapter.rest;

import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserManagementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserManagementController {
    private  final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/{id}")
    public User userNameById(@PathVariable String id){
        return userManagementService.getById(id).get();
    }
}

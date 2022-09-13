package com.example.greeting.rest.out.client;

import com.example.greeting.rest.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-management")
public interface UserClient {

    @GetMapping("/users/{id}")
    public UserDTO userInfo(@PathVariable String id);
}

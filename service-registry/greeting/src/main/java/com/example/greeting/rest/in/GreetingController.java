package com.example.greeting.rest.in;

import com.example.greeting.rest.dto.UserDTO;
import com.example.greeting.rest.out.client.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/greet")
public class GreetingController {
    private final static String UM_URL= "http://user-management/users/{id}";
    private final UserClient userClient;

    private final RestTemplate restTemplate;

    public GreetingController(UserClient userClient,
                              RestTemplate restTemplate) {
        this.userClient = userClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/v1/{id}")
    public String greetById(@PathVariable String id){
         return String.format("Hello,  %s", userClient.userInfo(id).getUserName());
    }

    @GetMapping("/v2/{id}")
    public String greetByIdv2(@PathVariable String id){
        UserDTO userDTO = restTemplate.getForObject(UM_URL, UserDTO.class, id);
        return String.format("Hello,  %s", userDTO.getUserName());
    }


}

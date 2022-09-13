package com.example.greeting.rest.in;

import com.example.greeting.rest.dto.UserDTO;
import com.example.greeting.rest.out.client.UserClient;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/greet")
public class GreetingController {
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final static String UM_URL= "http://user-management/users/{id}";
    private final UserClient userClient;

    private final RestTemplate restTemplate;

    public GreetingController(CircuitBreakerFactory circuitBreakerFactory, UserClient userClient,
                              RestTemplate restTemplate) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.userClient = userClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/cb/{id}")
    public String greetByIdCB(@PathVariable String id){
        return circuitBreakerFactory.create("cb_greet").run(
                () -> greetById(id),throwable -> defaultGreet()
        );
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
    public String defaultGreet(){
        return "Hello, Anonymous User";
    }

}

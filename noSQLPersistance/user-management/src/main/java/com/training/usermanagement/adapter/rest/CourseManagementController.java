package com.example.usermanagement.adapter.rest;

import com.example.usermanagement.commandBus.CommandBus;
import com.example.usermanagement.commands.CreateCourseCommand;
import reactive.src.main.java.com.example.usermanagement.exceptions.CourseNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.services.CourseManagementService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseManagementController {

    private final CourseManagementService courseManagementService;
    private final CommandBus commandBus;
    public CourseManagementController(CourseManagementService courseManagementService, CommandBus commandBus) {
        this.courseManagementService = courseManagementService;
        this.commandBus = commandBus;
    }

    @GetMapping("/{id}")
    public Course gettingById(@PathVariable Long id){
        return courseManagementService
                .byId(id)
                        .orElseThrow(CourseNotFoundException::new);
    }

    @SneakyThrows
    @PostMapping
    public Course createCourse(@RequestBody CreateCourseCommand command){

        return commandBus.execute(command);
    }
}

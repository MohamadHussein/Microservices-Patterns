package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.rest;

import reactive.src.main.java.com.example.usermanagement.exceptions.CourseNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.services.CourseManagementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseManagementController {

    private final CourseManagementService courseManagementService;

    public CourseManagementController(CourseManagementService courseManagementService) {
        this.courseManagementService = courseManagementService;
    }

    @GetMapping("/{id}")
    public Course gettingById(@PathVariable Long id){
        return courseManagementService
                .byId(id)
                        .orElseThrow(()-> new CourseNotFoundException());
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        System.out.println("course = " + course);
        return courseManagementService.addCourse(course);
    }
}

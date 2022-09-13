package reactive.src.main.java.com.example.usermanagement.adapter.rest;

import reactive.src.main.java.com.example.usermanagement.exceptions.CourseNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.services.CourseManagementService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/course")
public class CourseManagementController {

    private final CourseManagementService courseManagementService;

    public CourseManagementController(CourseManagementService courseManagementService) {
        this.courseManagementService = courseManagementService;
    }

    @GetMapping("/{id}")
    public Mono<Course> gettingById(@PathVariable Long id){
        return courseManagementService
                .byId(id)
                .handle((course, sink) -> {
                    if(course==null)sink.error(new CourseNotFoundException());
                    else sink.next(course);
                });
    }

    @PostMapping
    public Mono<Course> createCourse(@RequestBody Course course){
        System.out.println("course = " + course);
        return courseManagementService.addCourse(course);
    }
}

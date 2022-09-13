package reactive.src.main.java.com.example.usermanagement.adapter.rest;

import reactive.src.main.java.com.example.usermanagement.exceptions.StudentNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import reactive.src.main.java.com.example.usermanagement.services.StudentManagementService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentManagementController {
    private final StudentManagementService studentManagementService;

    public StudentManagementController(StudentManagementService studentManagementService) {
        this.studentManagementService = studentManagementService;
    }

    @GetMapping("/{id}")
    public Mono<Student> byId(@PathVariable Long id){
        return studentManagementService
                .getById(id)
                .handle((student, sink) ->{
                    if(student==null)sink.error(new StudentNotFoundException());
                    else sink.next(student);
                });
    }

    @GetMapping("/{id}/course")
    public Flux<Course> getCourses(@PathVariable Long id){
        return studentManagementService.getStudentCourses(id);
    }
    @PostMapping
    public Mono<Student> creating(@RequestBody Student student){
        return studentManagementService.create(student);
    }

    @PatchMapping("/{id}/course/{courseId}")
    public Flux<Course> enrollInCourse(@PathVariable Long id, @PathVariable Long courseId ){
        return studentManagementService.addCourse(id, courseId);
    }
}

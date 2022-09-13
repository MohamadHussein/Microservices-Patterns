package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.rest;

import reactive.src.main.java.com.example.usermanagement.exceptions.StudentNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import reactive.src.main.java.com.example.usermanagement.services.StudentManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentManagementController {
    private final StudentManagementService studentManagementService;

    public StudentManagementController(StudentManagementService studentManagementService) {
        this.studentManagementService = studentManagementService;
    }

    @GetMapping("/{id}")
    public Student byId(@PathVariable Long id){
        return studentManagementService
                .getById(id)
                .orElseThrow(() -> new StudentNotFoundException());
    }

    @GetMapping("/{id}/course")
    public List<Course> getCourses(@PathVariable Long id){
        return studentManagementService.getStudentCourses(id);
    }
    @PostMapping
    public Student creating(@RequestBody Student student){
        return studentManagementService.create(student);
    }

    @PatchMapping("/{id}/course/{courseId}")
    public List<Course> enrollInCourse(@PathVariable Long id, @PathVariable Long courseId ){
        return studentManagementService.addCourse(id, courseId);
    }
}

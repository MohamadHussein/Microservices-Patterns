package reactive.src.main.java.com.example.usermanagement.services;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
@Transactional
@Service
public class CourseManagementService {
    private final CourseRepo courseRepo;

    public CourseManagementService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Mono<Course> byId(Long id){
        return courseRepo.byId(id);
    }

    public Mono<Course> addCourse(Course course){
        return courseRepo.save(course);
    }
}

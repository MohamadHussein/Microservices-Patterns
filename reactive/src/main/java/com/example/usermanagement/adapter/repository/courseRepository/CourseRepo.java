package reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository;

import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepo {
    Mono<Course> byId(Long id);

    Mono<Course> save(Course course);

    Flux<Course> findByName(String name);

    Flux<Student> addStudentToCourse(Long courseId, Student student);
}

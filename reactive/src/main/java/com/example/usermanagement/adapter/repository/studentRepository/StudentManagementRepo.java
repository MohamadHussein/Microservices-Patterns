package reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository;

import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



public interface StudentManagementRepo {
    Mono<Student> byId(Long id);
    Flux<Student> findByCourseName(String courseName);
    Flux<Student> findByName(String name, Pageable pageable);

    Mono<Student> save(Student student);
    Flux<Course> addCourseToStudent(Long studentId, Course course);

}

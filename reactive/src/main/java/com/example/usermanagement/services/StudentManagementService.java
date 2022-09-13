package reactive.src.main.java.com.example.usermanagement.services;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.StudentManagementRepo;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional

public class StudentManagementService {

    private final StudentManagementRepo studentManagementRepo;
    private final CourseRepo courseRepo;

    public StudentManagementService(StudentManagementRepo studentManagementRepo, CourseRepo courseRepo) {
        this.studentManagementRepo = studentManagementRepo;
        this.courseRepo = courseRepo;
    }

    public Mono<Student> getById(Long id) {
        return studentManagementRepo
                .byId(id);
    }

    public Flux<Student> searchByName(String subString, Pageable pageable) {
        return studentManagementRepo.findByName(subString, pageable);
    }

    public Flux<Course> getStudentCourses(Long id) {
        return studentManagementRepo
                .byId(id)
//                .handle((student, sink) ->{
//                    if(student==null)sink.error(new StudentNotFoundException());
//                    else sink.next(student);
//                })
                .flatMapMany(student -> Flux.fromIterable(student.getCourses()))
                ;

    }

    public Flux<Student> getByCourseName(String courseName) {
        return studentManagementRepo.findByCourseName(courseName);
    }


    public Mono<Student> create(Student student) {
        return studentManagementRepo.save(student);
    }

    public Flux<Course> addCourse(Long studentId, Long courseId) {

        studentManagementRepo.byId(studentId)
                .flatMapMany(student -> courseRepo.addStudentToCourse(courseId,student)).subscribe();
        return courseRepo
                .byId(courseId)
                //                .handle((result, sink) -> {
                //                    if (result == null) sink.error(new CourseNotFoundException());
                //                    else sink.next(result);
                //                })
                .flatMapMany(course -> studentManagementRepo.addCourseToStudent(studentId, course));


    }
}

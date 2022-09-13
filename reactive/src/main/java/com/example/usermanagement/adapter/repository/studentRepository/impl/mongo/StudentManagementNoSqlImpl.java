package reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.StudentManagementRepo;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils.*;

@Component
@ConditionalOnProperty(value = "db.source.impl", havingValue = "mongo")
public class StudentManagementNoSqlImpl implements StudentManagementRepo {

    private final StudentManagementMongoImpl studentManagementMongo;

    public StudentManagementNoSqlImpl(StudentManagementMongoImpl studentManagementMongo) {
        this.studentManagementMongo = studentManagementMongo;

    }

    @Override
    public Mono<Student> byId(Long id) {
        return studentManagementMongo
                .findById(String.valueOf(id))
                .map(Utils::toStudentModel);
    }

    @Override
    public Flux<Student> findByCourseName(String courseName) {
        return studentManagementMongo
                .findByCoursesExists(courseName)
                .map(Utils::toStudentModel);
    }

    @Override
    public Flux<Student> findByName(String name, Pageable pageable) {
        return studentManagementMongo
                .findByFirstNameOrLastNameContaining(name, name, pageable)
                .map(Utils::toStudentModel);
    }

    @Override
    public Mono<Student> save(Student student) {
        //maintain relation modelling for documents

        StudentDoc studentDoc = toStudentDocument(student);
        System.out.println(studentDoc);
        return studentManagementMongo.save(studentDoc).map(Utils::toStudentModel);
    }

    @Override
    public Flux<Course> addCourseToStudent(Long studentId, Course course) {

        CourseDoc courseDoc = Utils.toCourseDocument(course);
        System.out.println("courseDoc = " + courseDoc);

        return studentManagementMongo
                .findById(String.valueOf(studentId))
                .doOnEach(System.out::println)
                .flatMap(
                        studentDoc -> {
                            studentDoc.addCourseToList(courseDoc);
                            System.out.println("studentDoc inner = " + studentDoc);
                            return studentManagementMongo
                                    .save(studentDoc);
                        }
                ).flatMapMany(studentDoc -> {
                            System.out.println("student DOC " +studentDoc);
                            return Flux.fromIterable(
                                    studentDoc.getCourses());
                        }
                ).map(Utils::toCourseModel);
    }
}

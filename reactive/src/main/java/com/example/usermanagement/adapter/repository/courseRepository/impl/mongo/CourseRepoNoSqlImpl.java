package reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils.*;

@Repository
@ConditionalOnProperty(value = "db.source.impl", havingValue = "mongo")
public class CourseRepoNoSqlImpl implements CourseRepo {

    private final CourseRepoMongoImpl courseRepoMongo;

    public CourseRepoNoSqlImpl(CourseRepoMongoImpl courseRepoMongo) {
        this.courseRepoMongo = courseRepoMongo;
    }

    @Override
    public Mono<Course> byId(Long id) {
        return courseRepoMongo.findById(String.valueOf(id)).map(Utils::toCourseModel);
    }

    @Override
    public Mono<Course> save(Course course) {
        CourseDoc courseDoc = toCourseDocument(course);
        return courseRepoMongo.save(courseDoc).map(Utils::toCourseModel);
    }

    @Override
    public Flux<Course> findByName(String name) {
        return courseRepoMongo.findByName(name).map(Utils::toCourseModel);
    }

    @Override
    public Flux<Student> addStudentToCourse(Long courseId, Student student) {
        StudentDoc studentDoc = toStudentDocument(student);
        return courseRepoMongo.findById(String.valueOf(courseId))
                .flatMap(courseDoc -> {
                    courseDoc.addStudentToCourse(studentDoc);
                    return courseRepoMongo.save(courseDoc);
                }).flatMapMany(courseDoc -> Flux.fromIterable(courseDoc.getStudents())
                ).map(Utils::toStudentModel);
    }
}

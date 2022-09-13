package reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface StudentManagementMongoImpl extends ReactiveMongoRepository<StudentDoc,String> {
    Flux<StudentDoc> findByCoursesExists(String name);
    Flux<StudentDoc> findByFirstNameOrLastNameContaining(String firstname, String lastname, Pageable pageable);
}

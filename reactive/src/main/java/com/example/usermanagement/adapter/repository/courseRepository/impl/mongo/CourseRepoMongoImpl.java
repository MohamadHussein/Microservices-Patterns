package reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CourseRepoMongoImpl extends ReactiveMongoRepository<CourseDoc,String> {
    Flux<CourseDoc> findByName(String name, Pageable pageable);
    Flux<CourseDoc> findByName(String name);

}

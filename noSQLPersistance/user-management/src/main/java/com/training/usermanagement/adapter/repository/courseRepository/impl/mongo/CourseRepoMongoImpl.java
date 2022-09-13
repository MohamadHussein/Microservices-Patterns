package com.example.usermanagement.adapter.repository.courseRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepoMongoImpl extends MongoRepository<CourseDoc,String> {
    Page<CourseDoc> findByName(String name, Pageable pageable);

}

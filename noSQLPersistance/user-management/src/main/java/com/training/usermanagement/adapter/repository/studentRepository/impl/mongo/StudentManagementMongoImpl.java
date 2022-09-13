package com.example.usermanagement.adapter.repository.studentRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface StudentManagementMongoImpl extends MongoRepository<StudentDoc,String> {
    List<StudentDoc> findByCoursesExists(String name);
    Page<StudentDoc> findByFirstNameOrLastNameContaining(String firstname, String lastname, Pageable pageable);
}

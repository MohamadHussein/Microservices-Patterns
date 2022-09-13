package com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents;


import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Collections;
import java.util.List;
@Getter
@Document(collection = "courses")
@Builder
public class CourseDoc {

    @Id
    private String id;
    private String name;

    private String lecturer;

    @DocumentReference
    private List<StudentDoc> students = Collections.emptyList();

}

package com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents;


import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Builder
@Document(collection = "students")
public class StudentDoc {
    @Id
    private String id;
    @Field("first")
    private String firstName;
    @Field("last")
    private String lastName;
    @Field("bday")
    private LocalDate birthdate;

    @DocumentReference
    private List<CourseDoc> courses;

    public List<CourseDoc> addCourseToList(CourseDoc course){
        this.courses.add(course);
        return courses;
    }
}

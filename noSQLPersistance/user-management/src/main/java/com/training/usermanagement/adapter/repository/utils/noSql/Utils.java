package com.example.usermanagement.adapter.repository.utils.noSql;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;

import java.util.stream.Collectors;

public class Utils {
    public  static Student toStudentModel(StudentDoc studentDoc){
        return Student.builder().id(
                Long.valueOf(
                    studentDoc.getId()
                ))
                .fullName(studentDoc.getFirstName() + " "+ studentDoc.getLastName())
                .birthdate(studentDoc.getBirthdate())
                .courses(
                        studentDoc.getCourses().stream()
                                .map(reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils::toCourseModel)
                                .collect(Collectors.toList()))
                .build();
    }

    public static Course toCourseModel(CourseDoc courseDoc) {
        return Course.builder()
                .id(Long.valueOf(courseDoc.getId()))
                .name(courseDoc.getName())
                .lecturerName(courseDoc.getLecturer())
                .studentList(courseDoc.getStudents().stream()
                        .map(reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils::toStudentModel)
                        .collect(Collectors.toList()))
                .build();
    }

    public static StudentDoc toStudentDocument(Student student){

        String[] fullName = student.getFullName().split("\\s");
        return StudentDoc.builder()
                .id(String.valueOf(student.getId()))
                .firstName(fullName[0])
                .birthdate(student.getBirthdate())
                .lastName(fullName[1])
                .courses(student.getCourses().stream().map(reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils::toCourseDocument).collect(Collectors.toList())
        ).build();
    }

    public static  CourseDoc toCourseDocument(Course course) {

        return CourseDoc.builder()
                .id(String.valueOf(course.getId()))
                .name(course.getName())
                .lecturer(course.getLecturerName())
                .students(course.getStudentList()
                        .stream()
                        .map(reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils::toStudentDocument)
                        .collect(Collectors.toList()))
                .build();

    }
}

package com.example.usermanagement.adapter.repository.studentRepository;

import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentManagementRepo {
    Optional<Student> byId(Long id);
    List<Student> findByCourseName(String courseName);
    Page<Student> findByName(String name, Pageable pageable);

    Student save(Student student);
    List<Course> addCourseToStudent(Long studentId, Course course);

}

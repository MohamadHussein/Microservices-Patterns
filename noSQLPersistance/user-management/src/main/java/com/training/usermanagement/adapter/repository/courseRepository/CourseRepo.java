package com.example.usermanagement.adapter.repository.courseRepository;

import reactive.src.main.java.com.example.usermanagement.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CourseRepo {
    Optional<Course> byId(Long id);

    Course save(Course course);

    Page<Course> findByName(String name, Pageable pageable);
}

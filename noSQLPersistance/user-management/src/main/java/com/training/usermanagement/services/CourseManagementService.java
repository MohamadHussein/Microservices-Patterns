package com.example.usermanagement.services;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseManagementService {
    private final CourseRepo courseRepo;

    public CourseManagementService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Optional<Course> byId(Long id){
        return courseRepo.byId(id);
    }

    public Course addCourse(Course course){
        return courseRepo.save(course);
    }
}

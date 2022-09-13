package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.courseRepository;

import reactive.src.main.java.com.example.usermanagement.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepo {
    Optional<Course> byId(Long id);

    Course save(Course course);

    List<Course> findByName(String name);
}

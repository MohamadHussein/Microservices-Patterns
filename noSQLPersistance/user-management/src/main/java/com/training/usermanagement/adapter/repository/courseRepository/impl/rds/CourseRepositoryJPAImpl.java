package com.example.usermanagement.adapter.repository.courseRepository.impl.rds;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.usermanagement.adapter.repository.utils.rds.Utils.toCourseEntity;
import static com.example.usermanagement.adapter.repository.utils.rds.Utils.toCourseModel;




@Component
@ConditionalOnProperty(value = "db.source.impl",havingValue = "h2",matchIfMissing = true)

public class CourseRepositoryJPAImpl implements CourseRepo {

    private final CourseRepositorySpringJPAImpl courseRepositorySpringJPA;

    public CourseRepositoryJPAImpl(CourseRepositorySpringJPAImpl courseRepositorySpringJPA) {
        this.courseRepositorySpringJPA = courseRepositorySpringJPA;
    }

    @Override
    public Optional<Course> byId(Long id) {
        return courseRepositorySpringJPA.findById(id).map(toCourseModel());
    }


    @Override
    public Course save(Course course) {
        System.out.println("course = " + course);
        CourseEntity entity = courseRepositorySpringJPA.save(toCourseEntity().apply(course));
        return toCourseModel().apply(entity);
    }


    @Override
    public Page<Course> findByName(String name, Pageable pageable) {
        return courseRepositorySpringJPA
                .findByNameContaining(name, pageable)
                .map(toCourseModel());
    }
}

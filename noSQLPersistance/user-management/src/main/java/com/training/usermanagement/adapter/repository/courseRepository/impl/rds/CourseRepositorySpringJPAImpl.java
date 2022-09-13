package com.example.usermanagement.adapter.repository.courseRepository.impl.rds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepositorySpringJPAImpl extends JpaRepository<CourseEntity,Long> {
    Page<CourseEntity> findByNameContaining(String substr, Pageable pageable);
}

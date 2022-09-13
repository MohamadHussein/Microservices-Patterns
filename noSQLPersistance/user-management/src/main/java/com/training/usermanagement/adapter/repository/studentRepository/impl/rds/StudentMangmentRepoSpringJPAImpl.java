package com.example.usermanagement.adapter.repository.studentRepository.impl.rds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentMangmentRepoSpringJPAImpl extends JpaRepository<StudentEntity,Long> {
    Page<StudentEntity> findByFirstNameContaining(String firstName, Pageable pageable);
    Page<StudentEntity> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Pageable pageable);
    List<StudentEntity>  findStudentBycoursesContaining(String courseName);

}
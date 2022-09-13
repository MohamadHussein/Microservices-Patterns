package com.example.usermanagement.adapter.repository.lecturerRepository.impl.rds;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepoSpringJPAImpl extends JpaRepository<LecturerEntity,Long> {
    List<LecturerEntity> findByFirstNameOrLastNameContaining(String firstname, String lastName);
}

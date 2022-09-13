package com.example.usermanagement.adapter.repository.courseRepository.impl.rds;

import com.example.usermanagement.adapter.repository.lecturerRepository.impl.rds.LecturerEntity;
import com.example.usermanagement.adapter.repository.studentRepository.impl.rds.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToOne
    private LecturerEntity lecturer;
    @ManyToMany
//    @JoinTable(name = "student_course",
//            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id") ,
//            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<StudentEntity> students = Collections.emptyList();
}
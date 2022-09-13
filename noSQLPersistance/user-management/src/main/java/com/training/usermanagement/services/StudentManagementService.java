package com.example.usermanagement.services;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.CourseRepo;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.StudentManagementRepo;
import reactive.src.main.java.com.example.usermanagement.exceptions.CourseNotFoundException;
import reactive.src.main.java.com.example.usermanagement.exceptions.StudentNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentManagementService {

    private final StudentManagementRepo studentManagementRepo;
    private final CourseRepo courseRepo;

    public StudentManagementService(StudentManagementRepo studentManagementRepo, CourseRepo courseRepo) {
        this.studentManagementRepo = studentManagementRepo;
        this.courseRepo = courseRepo;
    }

    public Optional<Student> getById(Long id){
        return studentManagementRepo
                .byId(id);
    }

    public Page<Student> searchByName(String subString, Pageable pageable){
        return studentManagementRepo.findByName(subString,pageable);
    }

    public List<Course> getStudentCourses(Long id){
        return studentManagementRepo
                .byId(id)
                .orElseThrow(()-> new StudentNotFoundException()).getCourses();
    }

    public List<Student> getByCourseName(String courseName){
        return studentManagementRepo.findByCourseName(courseName);
    }


    public Student create(Student student) {
        return studentManagementRepo.save(student);
    }

    public List<Course> addCourse(Long studentId, Long courseId) {
        Course course = courseRepo
                .byId(courseId)
                .orElseThrow(() -> new CourseNotFoundException());

        return studentManagementRepo.addCourseToStudent(studentId, course);
    }
}

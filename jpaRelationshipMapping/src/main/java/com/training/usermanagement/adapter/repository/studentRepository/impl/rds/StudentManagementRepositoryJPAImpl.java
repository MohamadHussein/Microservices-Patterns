package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.studentRepository.impl.rds;

import com.example.usermanagement.adapter.repository.Entities.StudentEntity;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.StudentManagementRepo;
import reactive.src.main.java.com.example.usermanagement.exceptions.StudentNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.usermanagement.adapter.repository.utils.Utils.*;


@Component
public class StudentManagementRepositoryJPAImpl implements StudentManagementRepo {

    private final StudentMangmentRepoSpringJPAImpl studentManagementRepoSpringJPA;

    public StudentManagementRepositoryJPAImpl(StudentMangmentRepoSpringJPAImpl studentManagementRepoSpringJPA) {
        this.studentManagementRepoSpringJPA = studentManagementRepoSpringJPA;
    }


    @Override
    public Optional<Student> byId(Long id) {

        return studentManagementRepoSpringJPA.findById(id).map(toStudentModel());

    }

    @Override
    public List<Student> findByCourseName(String courseName) {
        return studentManagementRepoSpringJPA.findStudentBycoursesContaining(courseName)
                .stream()
                .map(toStudentModel())
                .collect(Collectors.toList());
    }


    @Override
    public Page<Student> findByName(String name, Pageable pageable) {
        return studentManagementRepoSpringJPA
                .findByFirstNameContainingOrLastNameContaining(name,name,pageable)
                .map(toStudentModel());
    }

    @Override
    public Student save(Student student) {
        StudentEntity se = studentManagementRepoSpringJPA
                .saveAndFlush(toStudentEntity(student));


        return studentManagementRepoSpringJPA
                .findById(se.getId())
                .map(toStudentModel()).get();
    }

    @Override
    public List<Course> addCourseToStudent(Long studentId, Course course) {
        StudentEntity studentEntity = studentManagementRepoSpringJPA
                .findById(studentId).orElseThrow(() -> new StudentNotFoundException());
        System.out.println("studentEntity = " + studentEntity);
        studentEntity.addCourseToList(toCourseEntity().apply(course));
        System.out.println("studentEntity = " + studentEntity);
        System.out.println("studentEntity.getcourses() = " + studentEntity.getCourses());
        studentManagementRepoSpringJPA.saveAndFlush(studentEntity);
        System.out.println("studentManagementRepoSpringJPA.findById(studentEntity.getId()) = " + studentManagementRepoSpringJPA.findById(studentEntity.getId()));
        return studentEntity
                .getCourses()
                .stream().map(toCourseModel())
                .collect(Collectors.toList());
    }
    }


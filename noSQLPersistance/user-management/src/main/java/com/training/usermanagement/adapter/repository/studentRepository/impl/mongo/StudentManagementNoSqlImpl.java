package com.example.usermanagement.adapter.repository.studentRepository.impl.mongo;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.StudentManagementRepo;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils;
import reactive.src.main.java.com.example.usermanagement.exceptions.StudentNotFoundException;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils.toStudentDocument;
import static reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql.Utils.toStudentModel;

@Component
@ConditionalOnProperty(value = "db.source.impl",havingValue = "mongo")
public class StudentManagementNoSqlImpl implements StudentManagementRepo {

    private final StudentManagementMongoImpl studentManagementMongo;

    public StudentManagementNoSqlImpl(StudentManagementMongoImpl studentManagementMongo) {
        this.studentManagementMongo = studentManagementMongo;

    }

    @Override
    public Optional<Student> byId(Long id) {
        return studentManagementMongo.findById(String.valueOf(id)).map(Utils::toStudentModel);
    }

    @Override
    public List<Student> findByCourseName(String courseName) {
        return studentManagementMongo
                .findByCoursesExists(courseName)
                .stream()
                .map(Utils::toStudentModel).collect(Collectors.toList());
    }

    @Override
    public Page<Student> findByName(String name, Pageable pageable) {
        return studentManagementMongo
                .findByFirstNameOrLastNameContaining(name, name, pageable)
                .map(Utils::toStudentModel);
    }

    @Override
    public Student save(Student student) {
        //maintain relation modelling for documents

        StudentDoc studentDoc = toStudentDocument(student);
        StudentDoc save = studentManagementMongo.save(studentDoc);
        System.out.println(studentDoc);
        return toStudentModel(save);
    }

    @Override
    public List<Course> addCourseToStudent(Long studentId, Course course) {
        //maintain relation modelling for documents
        StudentDoc studentDoc = studentManagementMongo.findById(String.valueOf(studentId))
                .orElseThrow(() -> new StudentNotFoundException());
        CourseDoc courseDoc = Utils.toCourseDocument(course);
        studentDoc.addCourseToList(courseDoc);

        return studentManagementMongo.save(studentDoc)
                .getCourses()
                .stream()
                .map(Utils::toCourseModel).collect(Collectors.toList());
    }
}

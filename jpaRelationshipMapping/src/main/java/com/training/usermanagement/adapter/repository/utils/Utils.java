package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.utils;

import com.example.usermanagement.adapter.repository.Entities.CertificateEntity;
import com.example.usermanagement.adapter.repository.Entities.CourseEntity;
import com.example.usermanagement.adapter.repository.Entities.LecturerEntity;
import com.example.usermanagement.adapter.repository.Entities.StudentEntity;
import reactive.src.main.java.com.example.usermanagement.model.Certificate;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Lecturer;
import reactive.src.main.java.com.example.usermanagement.model.Student;

import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

    public static Function<StudentEntity, Student> toStudentModel() {
        return se -> Student.builder().id(se.getId())
                .birthdate(se.getBirthdate())
                .id(se.getId())
                .fullName(String.format("%s %s",se.getFirstName(),se.getLastName()))
                .courses(
                        se.getCourses().stream().map(toCourseModel()).collect(Collectors.toList())
                )

                .build();
    }

    public static  Function<CourseEntity, Course> toCourseModel() {
        return ce ->
            Course.builder().id(ce.getId())
                    .name(ce.getName())
//                    .lecturerName(ce.getLecturer().getFirstName()+" "+ ce.getLecturer().getLastName())
                    .studentList(ce.getStudents().stream().map(toStudentModel()).collect(Collectors.toList())).build();

    }

    public static Function<Student, StudentEntity> toStudentEntity(){
        return student ->  StudentEntity.builder().birthdate(student.getBirthdate())
                .firstName(student.getFullName().split(" ")[0])
                .lastName(student.getFullName().split(" ")[1])
                .id(student.getId())
                .courses(student.getCourses().stream().map(toCourseEntity()).collect(Collectors.toList())).build();
    }

    public static  StudentEntity toStudentEntity(Student student){
        return StudentEntity.builder()
                .birthdate(student.getBirthdate())
                .firstName(student.getFullName().split(" ")[0])
                .lastName(student.getFullName().split(" ")[1])
                .id(student.getId())
                .courses(student.getCourses().stream().map(toCourseEntity()).collect(Collectors.toList())).build();
    }

    public static Function<Course, CourseEntity > toCourseEntity() {
        return course ->  CourseEntity.builder()
                .students(
                        course.getStudentList()
                                .stream()
                        .map(toStudentEntity())
                        .collect(Collectors.toList()))
                .name(course.getName())
                .id(course.getId())
                .build();
    }

    public static  Function<CertificateEntity, Certificate> toCertificateModel() {
        return ce ->
                Certificate.builder().id(ce.getId())
                        .department(ce.getDepartment())
                        .graduate(toLecturerModel(ce.getGraduate()))
                        .institute(ce.getInstitute())
                        .issueDate(ce.getIssueDate())
                        .issueId(ce.getIssueId()).build();

    }

    public static  Function< Certificate,CertificateEntity> toCertificateEntity() {
        return c->
                CertificateEntity.builder().id(c.getId())
                        .department(c.getDepartment())
                        .graduate(toLecturerEntity(c.getGraduate()))
                        .institute(c.getInstitute())
                        .issueDate(c.getIssueDate())
                        .issueId(c.getIssueId()).build();

    }
    public static Lecturer toLecturerModel(LecturerEntity graduate) {
        return Lecturer.builder().id(graduate.getId())
                .birthdate(graduate.getBirthdate())
                .firstName(graduate.getFirstName())
                .lastName(graduate.getLastName())
                .certificate(graduate.getCertificate()
                        .stream()
                        .map(toCertificateModel())
                        .collect(Collectors.toList()))
                .build();
    }
    public static Function<LecturerEntity,Lecturer> toLecturerModel() {
        return lecturerEntity ->  Lecturer.builder().id(lecturerEntity.getId())
                .birthdate(lecturerEntity.getBirthdate())
                .firstName(lecturerEntity.getFirstName())
                .lastName(lecturerEntity.getLastName())
                .certificate(lecturerEntity.getCertificate()
                        .stream()
                        .map(toCertificateModel())
                        .collect(Collectors.toList()))
                .build();
    }

    public static LecturerEntity toLecturerEntity(Lecturer lecturer) {
        return LecturerEntity.builder().id(lecturer.getId())
                .birthdate(lecturer.getBirthdate())
                .firstName(lecturer.getFirstName())
                .lastName(lecturer.getLastName())
                .certificate(lecturer.getCertificate()
                        .stream()
                        .map(toCertificateEntity())
                        .collect(Collectors.toList()))
                .build();
    }

}

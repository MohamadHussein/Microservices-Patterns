package reactive.src.main.java.com.example.usermanagement.adapter.repository.utils.noSql;

import reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents.CourseDoc;
import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import reactive.src.main.java.com.example.usermanagement.model.Course;
import reactive.src.main.java.com.example.usermanagement.model.Student;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static Student toStudentModel(StudentDoc studentDoc) {
        System.out.println("studentDoc util = " + studentDoc);
        Student student = Student.builder()
                .id(Long.valueOf(studentDoc.getId()))
                .fullName(studentDoc.getFirstName() + " " + studentDoc.getLastName())
                .birthdate(studentDoc.getBirthdate())
                .courses(Stream.ofNullable(studentDoc.getCourses())
                        .flatMap(Collection::stream)
                        .map(Utils::toCourseModel)
                        .collect(Collectors.toList()))
                .build();

        System.out.println("student = " + student);
        return student;
    }

    public static Course toCourseModel(CourseDoc courseDoc) {
        System.out.println("courseDoc util= " + courseDoc);
        Course course = Course.builder()
                .id(Long.valueOf(courseDoc.getId()))
                .name(courseDoc.getName())
                .lecturerName(courseDoc.getLecturer())
                .studentList(Stream.ofNullable(courseDoc.getStudents())
                        .flatMap(Collection::stream)
                        .map(Utils::toStudentModel)
                        .collect(Collectors.toList()))
                .build();
        System.out.println("course = " + course);
        return course;

    }

    public static StudentDoc toStudentDocument(Student student) {

        String[] fullName = student.getFullName().split("\\s");
        StudentDoc studentDoc = StudentDoc.builder()
                .id(String.valueOf(student.getId()))
                .firstName(fullName[0])
                .birthdate(student.getBirthdate())
                .lastName(fullName[1])
                .courses(Stream.ofNullable(student.getCourses())
                        .flatMap(Collection::stream)
                        .map(Utils::toCourseDocument)
                        .collect(Collectors.toList()))
                .build();
        System.out.println("studentDoc = " + studentDoc);
        return studentDoc;
    }

    public static CourseDoc toCourseDocument(Course course) {

        System.out.println("course util = " + course);

        CourseDoc courseDoc = CourseDoc.builder()
                .id(String.valueOf(course.getId()))
                .name(course.getName())
                .lecturer(course.getLecturerName())
                .students(Stream.ofNullable(course.getStudentList())
                        .flatMap(Collection::stream)
                        .map(Utils::toStudentDocument)
                        .collect(Collectors.toList()))
                .build();

        System.out.println("courseDoc util = " + courseDoc);
        return courseDoc;
    }
}

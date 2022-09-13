package reactive.src.main.java.com.example.usermanagement.adapter.repository.courseRepository.impl.mongo.documents;


import reactive.src.main.java.com.example.usermanagement.adapter.repository.studentRepository.impl.mongo.documents.StudentDoc;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Getter
@Document(collection = "courses")
@Builder(toBuilder = true)
@ToString
public class CourseDoc {

    @Id
    private String id;
    private String name;

    private String lecturer;

    @DocumentReference
    private List<StudentDoc> students;

    public List<StudentDoc> addStudentToCourse(StudentDoc studentDoc){
        System.out.println("students = b4" + students);
        if(students ==null){
            this.students = new ArrayList<>();
            this.students.add(studentDoc);
        }else{
            this.students.add(studentDoc);
        }
        System.out.println("students = after" + students);
        return students;
    }

}

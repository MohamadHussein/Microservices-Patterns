package jpaRelationshipMapping.src.main.java.com.training.usermanagement.adapter.repository.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

    @ManyToMany
    private List<CourseEntity> courses;

    public List<CourseEntity> addCourseToList(CourseEntity courseEntity){
        this.courses.add(courseEntity);
        return courses;
    }

}

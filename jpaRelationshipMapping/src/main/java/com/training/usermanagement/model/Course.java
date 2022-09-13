package jpaRelationshipMapping.src.main.java.com.training.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Long id;
    private String name;

    private String lecturerName;
    private List<Student> studentList = Collections.emptyList();
}

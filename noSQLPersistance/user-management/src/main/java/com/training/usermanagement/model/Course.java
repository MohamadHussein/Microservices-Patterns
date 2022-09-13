package com.example.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Observable;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Course   {
    private Long id;
    private String name;

    private String lecturerName;
    private List<Student> studentList = Collections.emptyList();
}

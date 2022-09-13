package com.example.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String fullName;
    private LocalDate birthdate;
    private List<Course> courses = Collections.emptyList();
}

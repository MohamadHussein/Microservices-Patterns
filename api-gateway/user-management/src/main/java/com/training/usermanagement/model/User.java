package com.example.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String userName;
    private LocalDate birthdate;
}

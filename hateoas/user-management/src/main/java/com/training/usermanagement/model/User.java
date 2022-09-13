package com.example.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String id;
    private String userName;
    private LocalDate birthdate;
}

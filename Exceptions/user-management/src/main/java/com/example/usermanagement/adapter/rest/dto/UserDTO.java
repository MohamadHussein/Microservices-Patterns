package com.example.usermanagement.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {
    private String id;
    private String userName;
    private LocalDate birthdate;
}

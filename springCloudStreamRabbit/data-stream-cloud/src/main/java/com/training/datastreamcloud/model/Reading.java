package com.example.datastreamcloud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reading {
    private String id;
    private String sensorId;
    private LocalDateTime timestamp;
    private double reading;
}

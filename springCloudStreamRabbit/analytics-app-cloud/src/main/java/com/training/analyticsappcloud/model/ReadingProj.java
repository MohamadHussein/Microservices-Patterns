package com.example.analyticsappcloud.model;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@Value
public class ReadingProj {
     String sensorId;
     double reading;
    LocalDateTime timestamp;

}

package com.example.analyticsappcloud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Report {
    private String sensorId;
    private double sum;
    private double average;
    private double max;
    private int numOfReadings;
}

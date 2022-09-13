package com.example.analyticsappcloud.adapter.in.repository.impl;

import com.example.analyticsappcloud.adapter.in.repository.SensortReportRepo;
import com.example.analyticsappcloud.model.Report;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class SensorReportRepoImpInMem implements SensortReportRepo {

    private HashMap<String, Report> map = new HashMap<>();
    @Override
    public Optional<Report> getBySensorId(String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Optional<Report> save(Report report) {
        map.put(report.getSensorId(),report);
        return Optional.ofNullable(map.get(report.getSensorId()));
    }

}

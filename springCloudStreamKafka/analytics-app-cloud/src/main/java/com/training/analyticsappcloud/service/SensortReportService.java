package com.example.analyticsappcloud.service;

import com.example.analyticsappcloud.adapter.in.repository.SensortReportRepo;
import com.example.analyticsappcloud.model.Report;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensortReportService {

    private final SensortReportRepo sensortReportRepo;

    public SensortReportService(SensortReportRepo sensortReportRepo) {
        this.sensortReportRepo = sensortReportRepo;
    }

    public Optional<Report> get(String id) {
        return sensortReportRepo.getBySensorId(id);
    }

    public Optional<Report> save(Report report){
        return sensortReportRepo.save(report);
    }
}

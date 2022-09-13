package com.example.analyticsappcloud.adapter.in.rest;

import com.example.analyticsappcloud.model.Report;
import com.example.analyticsappcloud.service.SensortReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final SensortReportService sensorReportService;

    public AnalyticsController(SensortReportService sensorReportService) {
        this.sensorReportService = sensorReportService;
    }

    @GetMapping("/{id}")
    public Optional<Report> sensorReport(@PathVariable String id){
        return sensorReportService.get(id);
    }

}

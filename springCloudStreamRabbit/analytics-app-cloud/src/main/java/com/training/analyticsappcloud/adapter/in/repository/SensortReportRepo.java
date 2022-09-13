package com.example.analyticsappcloud.adapter.in.repository;

import com.example.analyticsappcloud.model.Report;

import java.util.Optional;

public interface SensortReportRepo {

    Optional<Report> getBySensorId(String id);

    Optional<Report> save(Report report);
}

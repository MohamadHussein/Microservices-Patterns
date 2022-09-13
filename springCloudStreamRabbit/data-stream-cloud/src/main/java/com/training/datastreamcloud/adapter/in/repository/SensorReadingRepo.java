package com.example.datastreamcloud.adapter.in.repository;

import com.example.datastreamcloud.model.Reading;

import java.util.List;

public interface SensorReadingRepo {
    List<Reading> save(Reading reading);

    List<Reading> getById(String id);
}

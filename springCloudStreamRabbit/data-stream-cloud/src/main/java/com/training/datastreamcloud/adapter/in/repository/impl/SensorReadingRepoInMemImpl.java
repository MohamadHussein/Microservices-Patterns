package com.example.datastreamcloud.adapter.in.repository.impl;


import com.example.datastreamcloud.adapter.in.repository.SensorReadingRepo;
import com.example.datastreamcloud.model.Reading;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SensorReadingRepoInMemImpl implements SensorReadingRepo {

    Map<String, List<Reading>> map = new ConcurrentHashMap<String,List<Reading>>() ;


    @Override
    public List<Reading> save(Reading reading) {
        return map.compute(reading.getSensorId(), (k, v) -> {
                    List<Reading> readings = v ==null ? new ArrayList<Reading>() : v;
                    readings.add(reading);
                    return readings;
                }
        );
    }


    @Override
    public List<Reading> getById(String id) {
        return map.get(id);
    }
}

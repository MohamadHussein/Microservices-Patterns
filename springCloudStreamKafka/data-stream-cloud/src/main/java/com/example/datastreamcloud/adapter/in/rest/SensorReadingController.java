package com.example.datastreamcloud.adapter.in.rest;

import com.example.datastreamcloud.model.Reading;
import com.example.datastreamcloud.service.SensorReadingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reading")
public class SensorReadingController {
    private final SensorReadingService sensorReadingService;

    public SensorReadingController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    @PostMapping
    public   List<Reading> createReading(@RequestBody Reading reading){

        return sensorReadingService.createReading(reading);
    }
    @GetMapping("/{id}")
    public List<Reading> fetchById(@PathVariable String id){
        return sensorReadingService.getReadingById(id);
    }
}

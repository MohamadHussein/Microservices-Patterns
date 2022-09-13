package com.example.datastreamcloud.service;

import com.example.datastreamcloud.adapter.in.repository.SensorReadingRepo;
import com.example.datastreamcloud.adapter.out.SensorReadingChannel;
import com.example.datastreamcloud.model.Reading;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@EnableBinding(SensorReadingChannel.class)
public class SensorReadingService {
    private final SensorReadingRepo sensorReadingRepo;
    private final SensorReadingChannel sensorReadingChannel;
    public SensorReadingService(SensorReadingRepo sensorReadingRepo, SensorReadingChannel sensorReadingChannel) {
        this.sensorReadingRepo = sensorReadingRepo;
        this.sensorReadingChannel = sensorReadingChannel;
    }

    public List<Reading> createReading(Reading reading) {
        HashMap<String, Object> headersMap = new HashMap<>();
        headersMap.put("sensor-id",reading.getId());
        sensorReadingChannel.readingSubmitted().send(
                MessageBuilder.createMessage(reading, new MessageHeaders(headersMap))
        );
        return sensorReadingRepo.save(reading);
    }

    public   List<Reading> getReadingById(String id) {
        return sensorReadingRepo.getById(id);
    }
}

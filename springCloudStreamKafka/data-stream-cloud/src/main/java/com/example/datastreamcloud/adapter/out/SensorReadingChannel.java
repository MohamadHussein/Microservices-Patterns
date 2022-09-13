package com.example.datastreamcloud.adapter.out;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SensorReadingChannel {
     String OUT_CHANNEL = "SENSOR_READING_SUBMITTED";

    @Output(OUT_CHANNEL)
    MessageChannel readingSubmitted();
}

package com.example.analyticsappcloud.adapter.in.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReadingEventChannel {
    String  REPORT_RECEIVED = "SENSOR-READING-RECEIVED";

    @Input(REPORT_RECEIVED)
    SubscribableChannel readingRec();
}

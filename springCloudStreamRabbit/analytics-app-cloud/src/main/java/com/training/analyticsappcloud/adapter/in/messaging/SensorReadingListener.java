package com.example.analyticsappcloud.adapter.in.messaging;

import com.example.analyticsappcloud.model.ReadingProj;
import com.example.analyticsappcloud.model.Report;
import com.example.analyticsappcloud.service.SensortReportService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

import static com.example.analyticsappcloud.adapter.in.messaging.ReadingEventChannel.REPORT_RECEIVED;

@Component
@EnableBinding(ReadingEventChannel.class)
public class SensorReadingListener {
    private final SensortReportService sensortReportService;

    public SensorReadingListener(SensortReportService sensortReportService) {
        this.sensortReportService = sensortReportService;
    }

    @StreamListener(REPORT_RECEIVED)
    public void parseReading(Message<ReadingProj> msg) {
        System.out.println("READING...");
        ReadingProj reading = msg.getPayload();
        System.out.println("reading = " + reading);
        Report report = Optional.ofNullable(reading)
                .filter(readingProj -> sensortReportService.get(readingProj.getSensorId()).isPresent())
                .map(updateReport())
                .orElse(
                        sensortReportService.save(
                                        Report.builder().sensorId(reading.getSensorId())
                                                .average(reading.getReading())
                                                .max(reading.getReading())
                                                .sum(reading.getReading())
                                                .numOfReadings(1)
                                                .build())
                                .orElseThrow(() -> new RuntimeException("can't create new report")));

        System.out.println("sensortReportService.save(report) = " + sensortReportService.save(report));

    }

    private Function<ReadingProj, Report> updateReport() {
        return reading -> sensortReportService.get(reading.getSensorId())
                .map(report -> report.toBuilder()
                        .sum(report.getSum() + reading.getReading())
                        .max(Math.max(report.getMax(), reading.getReading()))
                        .numOfReadings(report.getNumOfReadings() + 1 )
                        .average((report.getAverage() + reading.getReading()) / (report.getNumOfReadings()+ 1) ).build()).get();
    }

}

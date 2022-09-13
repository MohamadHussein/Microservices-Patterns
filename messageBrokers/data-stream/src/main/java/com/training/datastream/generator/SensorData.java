package com.aspire.datastream.generator;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Random;

import static com.aspire.datastream.configuration.RabbitConfiguration.*;

@Component
public class SensorData implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private static final String qPrefix = "factory.floor.motor.";


    public SensorData(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending Records ...");
        int count = 500;
        Random random = new Random();
        Sensor sensorType = Sensor.values()[0];
        while (count > 0) {

//            String routingKey = qPrefix+ sensorType.getRand().toString().toLowerCase();
//            System.out.println(routingKey);

            rabbitTemplate.convertAndSend(
                    DEFAULT_EXCHANGE_NAME,
                    "header-sensors-stream",
//
                    String.valueOf(random.nextGaussian() * 5 + 60)
            );
            Thread.sleep(10000);
            count--;
        }
    }
}

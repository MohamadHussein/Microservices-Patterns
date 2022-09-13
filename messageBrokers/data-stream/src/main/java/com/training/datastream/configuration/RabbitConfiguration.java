package com.aspire.datastream.configuration;

import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfiguration {

    static final String QUEUE_1_NAME = "heat-sensor-stream";
    static final String QUEUE_2_NAME = "vibration-sensor-stream";
    static final String QUEUE_3_NAME = "header-sensors-stream";
    static final String QUEUE_4_NAME = "all-sensors-stream";
    static final String ROUTING_KEY = "factory.floor.motor.heat";
    static final String ROUTING_2_KEY = "factory.floor.motor.vibration";
    static final String ROUTING_3_KEY = "factory.floor.motor.#";
    static final String ROUTING_4_KEY = "factory.floor.motor.#";
    public static final String TOPIC_EXCHANGE_NAME = "stream-topic";
    public static final String DIRECT_EXCHANGE_NAME = "stream-direct";
    public static final String FANOUT_EXCHANGE_NAME = "stream-fanout";
    public static final String HEADERS_EXCHANGE_NAME = "stream-headers";
    public static final String DEFAULT_EXCHANGE_NAME = "";

//    @Bean
//    public TopicExchange topicExchange(){
//
//        return new TopicExchange(TOPIC_EXCHANGE_NAME);
//    }
//
//    @Bean
//    public DirectExchange directExchange(){
//
//        return new DirectExchange(DIRECT_EXCHANGE_NAME);
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchange(){
//
//        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
//    }

//    @Bean
//    public HeadersExchange headersExchange(){
//
//        return new HeadersExchange(HEADERS_EXCHANGE_NAME);
//    }

    @Bean
    public DirectExchange defaultExchange(){ //default exchange is a direct exchange with no name

        return new DirectExchange(DEFAULT_EXCHANGE_NAME);
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_1_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_2_KEY);
    }

    @Bean
    public Queue queue2(){
        return new Queue(QUEUE_2_NAME);
    }

    @Bean
    public Binding binding2(Queue queue2, DirectExchange directExchange){
        return BindingBuilder.bind(queue2).to(directExchange).with(ROUTING_KEY);
    }

    @Bean
    public Queue queue3(){
        return new Queue(QUEUE_3_NAME);
    }

//    @Bean
//    public Binding binding3(Queue queue3, HeadersExchange headersExchange){
//        Map<String, Object> headerMap = new HashMap<>();
//        headerMap.put("s1", "HEAT");
//        headerMap.put("s2", "VIBRATION");
//        return BindingBuilder.bind(queue3).to(headersExchange).whereAll(headerMap).match();
//    }


}

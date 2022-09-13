package com.aspire.analyticsapp.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    static final String QUEUE_NAME = "heat-sensor-stream";
    static final String QUEUE_2_NAME = "vibration-sensor-stream";
    static final String QUEUE_3_NAME = "header-sensors-stream";


    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter adapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(adapter);
        return container;
    }


    @Bean
    public MessageListenerAdapter adapter(Receiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public Receiver receiver(){
        return new Receiver(QUEUE_NAME);
    }

    @Bean
    public SimpleMessageListenerContainer container1(ConnectionFactory connectionFactory, MessageListenerAdapter adapter1) {
        SimpleMessageListenerContainer container1 = new SimpleMessageListenerContainer();
        container1.setConnectionFactory(connectionFactory);
        container1.setQueueNames(QUEUE_3_NAME);
        container1.setMessageListener(adapter1);
        return container1;
    }


    @Bean
    public MessageListenerAdapter adapter1(Receiver receiver1){
        return new MessageListenerAdapter(receiver1, "receiveMessage");
    }

    @Bean
    public Receiver receiver1(){
        return new Receiver(QUEUE_3_NAME);
    }

}

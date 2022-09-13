package com.aspire.analyticsapp.Configuration;

public class Receiver {
    private final String queueName;

    public Receiver(String queueName) {
        this.queueName = queueName;
    }

    public void receiveMessage(String msg){
        System.out.printf("Message received on queue:: %s with payload:: %s\n",
                queueName,msg);
    }
}

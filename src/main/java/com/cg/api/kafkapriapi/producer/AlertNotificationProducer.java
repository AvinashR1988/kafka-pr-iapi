package com.cg.api.kafkapriapi.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertNotificationProducer {

    private static final String TOPIC = "HighPriorityTopic";

    @Autowired
    private KafkaTemplate<String, String> template;

    public void writeMessage(String message){
        System.out.println("Message pushing to topic "+ TOPIC);
        this.template.send(TOPIC, message);
    }
}

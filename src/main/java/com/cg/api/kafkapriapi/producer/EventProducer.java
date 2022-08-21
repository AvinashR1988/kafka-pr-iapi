package com.cg.api.kafkapriapi.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    private static final String TOPIC = "topic1";

    @Autowired
    private KafkaTemplate<String, String> template;

    public void writeMessage(String message){
        System.out.println("Message pushing to topic "+ TOPIC);
        this.template.send(TOPIC, message);
    }

}

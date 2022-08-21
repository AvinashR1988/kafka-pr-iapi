package com.cg.api.kafkapriapi.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {
    @KafkaListener(topics = "topic1", groupId = "default_gr_id")
    public void getMessage(String message) {
        System.out.println(message + " From Consumer 1");
    }
}

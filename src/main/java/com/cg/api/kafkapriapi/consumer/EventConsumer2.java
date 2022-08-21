package com.cg.api.kafkapriapi.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer2 {
    @KafkaListener(topics = "HighPriorityTopic", groupId = "default_gr_id1")
    public void getMessage(String message) {
        System.out.println(message + " From Consumer 2");
    }
}

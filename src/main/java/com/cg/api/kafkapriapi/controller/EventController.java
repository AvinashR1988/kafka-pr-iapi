package com.cg.api.kafkapriapi.controller;

import com.cg.api.kafkapriapi.producer.AlertNotificationProducer;
import com.cg.api.kafkapriapi.producer.EventProducer;
import com.cg.api.kafkapriapi.producer.MyKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    private EventProducer producer;

    @Autowired
    private MyKafkaProducer producer2;

    @Autowired
    private AlertNotificationProducer alertNotificationProducer;

    @PostMapping("/publish/{message}")
    public  void writeMessageToTopic(@PathVariable("message") String message) {
            this.producer.writeMessage(message);
    }

    @GetMapping("/fire-events")
    public  void fireEventsToTopic() {
        this.producer2.produce();
    }

    @GetMapping("/publish/{highPriorityMessage}")
    public  void writeMessageToHighPTopic(@PathVariable("highPriorityMessage") String highPriorityMessage) {
        this.alertNotificationProducer.writeMessage(highPriorityMessage);
    }

    public void setProducer(EventProducer producer) {
        this.producer = producer;
    }

}

package com.cg.api.kafkapriapi.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MyKafkaProducer {

    private static final String TOPIC = "topic2";
    private Producer<String, String> producer;

    public MyKafkaProducer(Properties properties) {
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("transactional.id","my-transactional-id");
        producer = new KafkaProducer<>(properties, new StringSerializer(), new StringSerializer());
    }

    public void produce() {
        this.producer.initTransactions();
        try {
            this.producer.beginTransaction();

            for (int i = 0; i < 100; i++)
                producer.send(new ProducerRecord<>(TOPIC, Integer.toString(i), Integer.toString(i)));

            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {

            producer.close();
        } catch (KafkaException e) {

            producer.abortTransaction();
        }
        producer.close();
    }


}
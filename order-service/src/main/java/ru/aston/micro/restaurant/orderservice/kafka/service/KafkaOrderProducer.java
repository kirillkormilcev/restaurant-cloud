package ru.aston.micro.restaurant.orderservice.kafka.service;

import static lombok.AccessLevel.PRIVATE;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class KafkaOrderProducer {

  final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {kafkaTemplate.send(topic, message);}
}

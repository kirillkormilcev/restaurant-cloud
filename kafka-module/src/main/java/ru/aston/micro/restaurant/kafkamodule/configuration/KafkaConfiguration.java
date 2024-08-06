package ru.aston.micro.restaurant.kafkamodule.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

  @Value("${topics.payment.name}")
  private String paymentTopic;

  @Value("${topics.restaurant.name}")
  private String restaurantTopic;

  @Bean
  public NewTopic paymentTopic() {
    return TopicBuilder.name(paymentTopic).build();
  }

  @Bean
  public NewTopic restaurantTopic() {
    return TopicBuilder.name(restaurantTopic).build();
  }
}

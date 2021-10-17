package com.tailenders.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public NewTopic productTopic() {
    return TopicBuilder.name("products").partitions(2).replicas(1).build();
  }

  @Bean
  public NewTopic orderTopic() {
    return TopicBuilder.name("orders").partitions(2).replicas(1).build();
  }
}

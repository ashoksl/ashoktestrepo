package com.tailenders.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class Listener {

  private static final Logger logger = LoggerFactory.getLogger(Listener.class);

  @KafkaListener(id = "product_listener", topics = {"products"}, groupId = "demo")
  public void consumeProducts(String productInfo) {
    logger.info("Product received. " + productInfo);
    //create product in the database
  }

  @KafkaListener(id = "order_listener", topics = {"orders"}, groupId = "demo")
  public void consumeOrders(String orderInfo) {
    logger.info("Order received. " + orderInfo);
    //validate and create an order
  }
}

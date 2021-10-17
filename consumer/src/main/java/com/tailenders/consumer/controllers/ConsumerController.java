package com.tailenders.consumer.controllers;

import com.tailenders.consumer.dto.ConsumerRequest;
import com.tailenders.consumer.dto.ConsumerResponse;
import com.tailenders.consumer.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
public class ConsumerController {

  @Autowired
  KafkaListenerEndpointRegistry registry;

  @Autowired
  KafkaTemplate<String, String> template;

  @PostMapping(value = "/send", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public void send(@RequestBody MessageRequest request) {
    template.send(request.getTopicName(), request.getMessage());
  }

  @PostMapping(value = "/stop", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ConsumerResponse stopConsumer(@RequestBody ConsumerRequest request) {
    MessageListenerContainer topicContainer = registry.getListenerContainer(request.getListenerId());
    topicContainer.stop();
    return new ConsumerResponse(request.getListenerId(), topicContainer.isRunning());
  }

  @PostMapping(value = "/start", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ConsumerResponse startConsumer(@RequestBody ConsumerRequest request) {
    MessageListenerContainer topicContainer = registry.getListenerContainer(request.getListenerId());
    topicContainer.start();
    return new ConsumerResponse(request.getListenerId(), topicContainer.isRunning());
  }

  @GetMapping(value = "/status", produces = APPLICATION_JSON_VALUE)
  public List<ConsumerResponse> consumerStatus() {
    return registry.getAllListenerContainers().stream()
        .map(container -> new ConsumerResponse(container.getListenerId(), container.isRunning()))
        .collect(Collectors.toList());
  }
}

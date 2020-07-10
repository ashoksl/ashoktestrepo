package com.tailenders.redisdemo;

import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RedissonClient syncRedisClient() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    return Redisson.create(config);
  }

  @Bean
  public KV kvClient() {
    Client client = Client.builder().endpoints("http://localhost:2379").build();
    return client.getKVClient();
  }
}

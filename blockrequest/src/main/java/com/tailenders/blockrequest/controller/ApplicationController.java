package com.tailenders.blockrequest.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.tailenders.blockrequest.GetResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

  ExecutorService executorService = Executors.newFixedThreadPool(2);

  CountDownLatch countDownLatch = new CountDownLatch(1);

  @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public GetResponse get() throws InterruptedException {
    GetResponse getResponse = new GetResponse();
    executorService.execute(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      getResponse.setCompleted(true);
      getResponse.setMessage("It took more than 1 second to complete");
      countDownLatch.countDown(); //Decreased the counter by 1
    });
    countDownLatch.await(2, TimeUnit.SECONDS);  //Current thread will wait counter to set to 0 or max of 2 seconds
    return getResponse;
  }
}

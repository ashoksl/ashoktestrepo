package com.tailender.leavecalendar;

import com.mongodb.client.MongoClient;
import com.tailender.leavecalendar.constants.LeaveConstants;
import com.tailender.leavecalendar.resource.Leave;
import com.tailender.leavecalendar.resource.ResourceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Main {

  @Autowired
  private MongoClient mongoClient;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  public RouterFunction routes(ResourceHandler resourceHandler) {
    return route(
        RequestPredicates.GET(LeaveConstants.URL_GET_LEAVES_OF_PERSON), request ->
            resourceHandler.getLeavesOfPerson(request.pathVariable(LeaveConstants.VAR_PATH_PERSONID)))
        .and(
            route(RequestPredicates.GET(LeaveConstants.URL_GET_LEAVES_OF_ROOM), request ->
                resourceHandler.getLeavesOfRoom(request.pathVariable(LeaveConstants.VAR_PATH_ROOMID))))
        .and(
            route(RequestPredicates.POST(LeaveConstants.URL_POST_LEAVE), request ->
                resourceHandler.reportLeave(request.bodyToMono(Leave.class))))
        .and(
            route(RequestPredicates.POST(LeaveConstants.URL_PUT_LEAVE), request ->
                resourceHandler.reportLeave(request.bodyToMono(Leave.class))));
  }

  @Bean
  public ReactiveMongoTemplate reactiveMongoTemplate() {
    return new ReactiveMongoTemplate(mongoClient, "asd");
  }
}

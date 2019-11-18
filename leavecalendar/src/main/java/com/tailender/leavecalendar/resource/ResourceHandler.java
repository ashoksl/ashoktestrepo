package com.tailender.leavecalendar.resource;

import com.tailender.leavecalendar.api.CalendarResource;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ResourceHandler implements CalendarResource {

  LeaveRepository leaveRepository;

  @Override
  public Mono<ServerResponse> getLeavesOfPerson(String personId) {
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromObject(personId));
  }

  @Override
  public Mono<ServerResponse> getLeavesOfRoom(String roomId) {
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromObject(roomId));
  }

  @Override
  public Mono<ServerResponse> reportLeave(Mono<Leave> monoLeave) {
    Mono<Leave> fromDb = monoLeave.flatMap(leaveRepository::save);
    return ServerResponse.ok().body(fromDb, Leave.class);
  }

  @Override
  public Mono<ServerResponse> modifyLeave(String personId, String dateAsString) {
    return null;
  }
}

package com.tailender.leavecalendar.api;

import com.tailender.leavecalendar.resource.Leave;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface CalendarResource {

  Mono<ServerResponse> getLeavesOfPerson(@PathVariable(value = "personId") String personId);

  Mono<ServerResponse> getLeavesOfRoom(@PathVariable(value = "roomId")String roomId);

  Mono<ServerResponse> reportLeave(Mono<Leave> monoLeave);

  Mono<ServerResponse> modifyLeave(@PathVariable(value = "personId")String personId, @RequestParam(value = "date") String dateAsString);
}

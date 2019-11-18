package com.tailender.leavecalendar.resource;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LeaveRepository extends ReactiveCrudRepository<Leave, String> {

}

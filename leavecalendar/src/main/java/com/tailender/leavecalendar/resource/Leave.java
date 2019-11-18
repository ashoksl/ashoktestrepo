package com.tailender.leavecalendar.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {

  @Id
  private String personId;

  private String roomId;

  private String date;

  private String notes;
}

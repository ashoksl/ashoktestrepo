package com.tailender.gcpbasicbot.resource;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.tailender.gcpbasicbot.dto.BotEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class BotResource {

  private static final Logger logger = Logger.getLogger(BotResource.class.getName());
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  @GetMapping("/getsome")
  public String getSample() {
    return "yeahitsme";
  }


  @PostMapping(value = "bot", produces = "application/json", consumes = "application/json")
  public ResponseEntity<String> processBot(@RequestBody String request) throws IOException {

    BotEvent event = JSON_FACTORY.fromString(request, BotEvent.class);
    logger.info(event.toPrettyString());
    String replyText = "";
    switch (event.type) {
      case "ADDED_TO_SPACE":
        String spaceType = event.space.getType();
        if ("ROOM".equals(spaceType)) {
          replyText = String.format("Thanks for adding me to %s", event.space.getDisplayName());
        } else {
          replyText = String.format("Thanks for adding me to a DM, %s!",
              event.user.getDisplayName());
        }
        break;
      case "MESSAGE":
        replyText = String.format("Your message: %s", event.message.getText());
        break;
      case "REMOVED_FROM_SPACE":
        logger.info(String.format("Bot removed from %s", event.space.getName()));
        break;
      default:
        replyText = "Cannot determine event type";
        break;
    }
    ResponseEntity<String> responseEntity = new ResponseEntity<>(replyText, HttpStatus.OK);
    return responseEntity;
  }
}

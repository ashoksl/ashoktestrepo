package com.tailender.gcpbasicbot.dto;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.api.services.chat.v1.model.Message;
import com.google.api.services.chat.v1.model.Space;
import com.google.api.services.chat.v1.model.User;

public class BotEvent extends GenericJson {

  @Key
  public String type;

  @Key
  public String eventTime;

  @Key
  public Space space;

  @Key
  public User user;

  @Key
  public Message message;

  @Key
  public String token;

  @Key
  public String configCompleteRedirectUrl;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getEventTime() {
    return eventTime;
  }

  public void setEventTime(String eventTime) {
    this.eventTime = eventTime;
  }

  public Space getSpace() {
    return space;
  }

  public void setSpace(Space space) {
    this.space = space;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getConfigCompleteRedirectUrl() {
    return configCompleteRedirectUrl;
  }

  public void setConfigCompleteRedirectUrl(String configCompleteRedirectUrl) {
    this.configCompleteRedirectUrl = configCompleteRedirectUrl;
  }
}

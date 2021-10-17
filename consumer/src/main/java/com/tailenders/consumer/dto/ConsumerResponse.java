package com.tailenders.consumer.dto;

public class ConsumerResponse {

  private String topicName;
  private boolean runningStatus;

  public ConsumerResponse(String topicName, boolean status) {
    this.topicName = topicName;
    this.runningStatus = status;
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public boolean getRunningStatus() {
    return runningStatus;
  }

  public void setRunningStatus(boolean runningStatus) {
    this.runningStatus = runningStatus;
  }
}

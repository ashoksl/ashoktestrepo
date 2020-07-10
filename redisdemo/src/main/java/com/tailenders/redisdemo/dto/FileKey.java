package com.tailenders.redisdemo.dto;

public class FileKey {

  private String key;
  private String filePath;

  public FileKey() {
  }

  public FileKey(String key, String filePath) {
    this.key = key;
    this.filePath = filePath;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}

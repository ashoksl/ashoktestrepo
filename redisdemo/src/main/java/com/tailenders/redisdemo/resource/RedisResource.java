package com.tailenders.redisdemo.resource;

import com.tailenders.redisdemo.dto.FileKey;
import com.tailenders.redisdemo.dto.StringKeyValue;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
public class RedisResource {

  @Autowired
  RedissonClient redissonClient;

  @PostMapping("/str")
  public void postKeyValue(@RequestBody StringKeyValue stringKeyValue) {
    RMap<String, String> redisMap = redissonClient.getMap("strKeyMap");
    redisMap.put(stringKeyValue.getKey(), stringKeyValue.getValue());
  }

  @GetMapping("/str")
  public List<StringKeyValue> getKeyValues() {
    RMap<String, String> redisMap = redissonClient.getMap("strKeyMap");
    List<StringKeyValue> listOfKeyValues = new ArrayList<>();
    redisMap.forEach((key, value) -> listOfKeyValues.add(new StringKeyValue(key, value)));
    return listOfKeyValues;
  }

  @GetMapping("/str/{key}")
  public StringKeyValue getKeyValue(@PathVariable("key") String ipKey) {
    RMap<String, String> redisMap = redissonClient.getMap("strKeyMap");
    return new StringKeyValue(ipKey, redisMap.get(ipKey));
  }

  @PostMapping("/file")
  public void postFilePath(@RequestBody FileKey fileKey) throws IOException {
    RBinaryStream fileStream = redissonClient.getBinaryStream(fileKey.getKey());
    byte[] fileBytes = Files.readAllBytes(Paths.get(fileKey.getFilePath()));
    fileStream.set(fileBytes);
  }

  @GetMapping("/file/{key}")
  public String getFileContent(@PathVariable String key) throws IOException {
    RBinaryStream fileStream = redissonClient.getBinaryStream(key);
    return new String(fileStream.get());
  }
}

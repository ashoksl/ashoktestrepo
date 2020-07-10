package com.tailenders.redisdemo.resource;

import com.tailenders.redisdemo.dto.StringKeyValue;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@Component
public class EtcdResource {

  @Autowired
  KV kvClient;

  @PostMapping("/kv")
  public void postKeyValue(@RequestBody StringKeyValue stringKeyValue) throws ExecutionException, InterruptedException {
    String modKey = "_authz." + stringKeyValue.getKey();
    ByteSequence key = ByteSequence.from(modKey.getBytes());
    ByteSequence value = ByteSequence.from(stringKeyValue.getValue().getBytes());
    kvClient.put(key, value).get();
  }

  @GetMapping("/kv")
  public List<StringKeyValue> getKeyValues() throws ExecutionException, InterruptedException {
    ByteSequence key = ByteSequence.from("_authz.".getBytes());
    GetOption prefixOption = GetOption.newBuilder().withPrefix(key).build();
    GetResponse getResponse = kvClient.get(key, prefixOption).get();
    return getResponse.getKvs().stream()
        .map(keyValue -> new StringKeyValue(asString(keyValue.getKey()), asString(keyValue.getValue()))).collect(Collectors.toList());
  }

  private String asString(ByteSequence byteSequence) {
    return new String(byteSequence.getBytes());
  }
}

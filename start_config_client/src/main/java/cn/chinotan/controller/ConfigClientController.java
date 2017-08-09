package cn.chinotan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

  @Value("${key}")
  private String key;

  @GetMapping("/key")
  public String getProfile() {
    return this.key;
  }
}

package cn.chinotan.publish;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

  @Value("${key}")
  private String key;

  @GetMapping("/key")
  public String getProfile() {
    return this.key;
  }
}

package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Start_eureka_ha {
  public static void main(String[] args) {
    SpringApplication.run(Start_eureka_ha.class, args);
  }
}

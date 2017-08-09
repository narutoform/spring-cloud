package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Start_config_server {

    public static void main(String[] args) {
        SpringApplication.run(Start_config_server.class, args);
    }
    
}

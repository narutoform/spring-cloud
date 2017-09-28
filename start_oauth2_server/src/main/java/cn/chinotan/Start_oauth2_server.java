package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Start_oauth2_server {

    public static void main(String[] args) {
        SpringApplication.run(Start_oauth2_server.class, args);
    }
    
}

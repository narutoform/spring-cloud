package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Start_config_client_eureka {

    public static void main(String[] args) {
        SpringApplication.run(Start_config_client_eureka.class, args);
    }
    
}

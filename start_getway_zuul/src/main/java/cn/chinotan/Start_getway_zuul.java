package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Start_getway_zuul {

    public static void main(String[] args) {
        SpringApplication.run(Start_getway_zuul.class, args);
    }
    
}

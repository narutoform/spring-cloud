package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class Movie_manager_ribbon_properties_without_eureka {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){ //最好写成restTemplate()
        return new RestTemplate();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Movie_manager_ribbon_properties_without_eureka.class, args);
    }
    
}

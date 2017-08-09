package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class Movie_manager_hystrix_turbine {
    
    public static void main(String[] args) {
        SpringApplication.run(Movie_manager_hystrix_turbine.class, args);
    }
    
}

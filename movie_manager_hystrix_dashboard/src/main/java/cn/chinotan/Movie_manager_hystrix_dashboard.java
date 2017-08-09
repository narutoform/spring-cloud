package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Movie_manager_hystrix_dashboard {
    
    public static void main(String[] args) {
        SpringApplication.run(Movie_manager_hystrix_dashboard.class, args);
    }
    
}

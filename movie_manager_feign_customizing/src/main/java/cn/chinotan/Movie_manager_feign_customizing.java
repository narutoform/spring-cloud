package cn.chinotan;

import cn.chinotan.annotation.RadomRibbon;
import cn.chinotan.config.RadomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "userAdmin", configuration = RadomConfig.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = RadomRibbon.class))
@EnableFeignClients
public class Movie_manager_feign_customizing {
    
    public static void main(String[] args) {
        SpringApplication.run(Movie_manager_feign_customizing.class, args);
    }
    
}

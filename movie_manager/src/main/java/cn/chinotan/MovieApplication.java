package cn.chinotan;

import cn.chinotan.annotation.RadomRibbon;
import cn.chinotan.config.RadomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "userAdmin", configuration = RadomConfig.class)// 这个只是设置负载均衡策略时用的不加也行
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = RadomRibbon.class))
public class MovieApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){ //最好写成restTemplate()
        return new RestTemplate();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
    
}

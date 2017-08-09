package cn.chinotan.controller;

import cn.chinotan.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    
    @Autowired
    RestTemplate template;
    
    @Autowired
    LoadBalancerClient loadBalancerClient;
    
    Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/movie/user/{id}")
    @HystrixCommand(fallbackMethod = "getUserFallback")
    public User getUser(@PathVariable Long id) {
        User user = template.getForObject("http://userAdmin/user/" + id, User.class);

        return user;
    }

    @GetMapping("/movie/test")
    public String getUserTest() {
        ServiceInstance userAdmin = loadBalancerClient.choose("userAdmin");

        logger.info("host:{} + port:{}", userAdmin.getHost(), userAdmin.getPort());

        return "log";
    }
    
    // 切记方法参数和返回值一致
    public User getUserFallback(Long id){
        User user = new User();
        user.setId(-1L);
        user.setName("不存在");

        return user;
    }

}

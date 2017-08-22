package cn.chinotan.publish;

import cn.chinotan.entity.User;
import cn.chinotan.feign.UserFeignClient;
import cn.chinotan.feign.UserFeignClient2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    UserFeignClient userFeignClient;
    
    @Autowired
    LoadBalancerClient loadBalancerClient;
    
    @Autowired
    UserFeignClient2 userFeignClient2;
    
    Logger logger = LoggerFactory.getLogger(MovieController.class);
    
    @GetMapping("/movie/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userFeignClient2.getOne(id);

        return user;
    }

    @GetMapping("/movie/test")
    public String getUserTest() {
        ServiceInstance userAdmin = loadBalancerClient.choose("userAdmin");

        logger.info("host:{} + port:{}", userAdmin.getHost(), userAdmin.getPort());

        return "log";
    }
}

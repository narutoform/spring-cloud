package cn.chinotan.publish;

import cn.chinotan.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    @RequestMapping("/movie/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = template.getForObject("http://userAdmin/user/" + id, User.class);

        return user;
    }

    @RequestMapping("/movie/test")
    public String getUserTest() {
        ServiceInstance userAdmin = loadBalancerClient.choose("userAdmin");

        logger.info("host:{} + port:{}", userAdmin.getHost(), userAdmin.getPort());

        return "log";
    }

}

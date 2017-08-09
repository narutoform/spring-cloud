package cn.chinotan.feign;

import cn.chinotan.entity.User;
import config.Configuration1;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "userAdmin1")
public interface UserFeignClient {
    
    // 使用feign后，直接可以负载均衡，不用加@LoadBalanced
    // 现在的版本支持GetMapping了,但@PathVariable还得加（“”）
    //@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @GetMapping("/user/{id}")
    public User getOne(@PathVariable("id") Long id);
    
}

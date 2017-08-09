package cn.chinotan.feign;

import cn.chinotan.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 失效转移和熔断是两个过程，可以失效但不溶断，熔断的话得加hystrix并且加@EnableCircuitBroke
@FeignClient(name = "microservice-provider-user", fallbackFactory = HystrixClientFallback.class)
public interface UserFeignClient {
  @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);
}

package cn.chinotan.feign;

import cn.chinotan.entity.User;
import config.Configuration1;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "userAdmin", configuration = Configuration1.class)
public interface UserFeignClient2 {
    
    // 这里必须大写GET
    // FeignClient的name不能和其他的FeignClient一样，比如讲UserFeignClient的name改为userAdmin1才行
    @RequestLine("GET /user/{id}")
    public User getOne(@Param("id") Long id);
    
}

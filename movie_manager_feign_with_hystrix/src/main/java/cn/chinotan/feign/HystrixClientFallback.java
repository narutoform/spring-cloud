package cn.chinotan.feign;

import cn.chinotan.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements UserFeignClient{

    @Override
    public User getOne(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setName("不存在");
        
        return user;
    }
}

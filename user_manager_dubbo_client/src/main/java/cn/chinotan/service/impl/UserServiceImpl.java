package cn.chinotan.service.impl;

import cn.chinotan.entity.User;
import cn.chinotan.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl{

    @Reference
    UserService userService;
    
    public User getOne(Long id){
        User user = userService.getOne(id);

        return user;
    }
    
}

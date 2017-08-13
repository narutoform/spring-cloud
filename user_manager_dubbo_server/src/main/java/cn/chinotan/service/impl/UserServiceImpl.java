package cn.chinotan.service.impl;

import cn.chinotan.dao.UserDao;
import cn.chinotan.entity.User;
import cn.chinotan.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    
    @Override
    public User getOne(Long id) {
        User user = userDao.findOne(id);//区别于getOne,注意区别，getOne得到的是个代理
        
        return user;
    }
}

package cn.chinotan.publish;

import cn.chinotan.dao.UserDao;
import cn.chinotan.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    UserDao userDao;
    
    Logger logger = Logger.getLogger(UserController.class);
    
    @GetMapping("/user/{id}")
    public Object getOne(@PathVariable Long id){
        User user = userDao.findOne(id);//区别于getOne,注意区别，getOne得到的是个代理

        logger.info(user.toString());
        return user;
    }
}

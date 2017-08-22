package cn.chinotan.publish;

import cn.chinotan.entity.User;
import cn.chinotan.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    UserServiceImpl userService;
    
    Logger logger = Logger.getLogger(UserController.class);
    
    @GetMapping("/user/reference/{id}")
    public Object getOne(@PathVariable Long id){
        User user = userService.getOne(id);

        logger.info(user.toString());
        return user;
    }
}

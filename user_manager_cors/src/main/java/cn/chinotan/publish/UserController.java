package cn.chinotan.publish;

import cn.chinotan.dao.UserDao;
import cn.chinotan.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    Logger logger = Logger.getLogger(UserController.class);

    @GetMapping("/user/{id}")
    // @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST}) // 第一种跨域请求方法
    public Object getOne(@PathVariable Long id) {
        User user = userDao.findOne(id);

        logger.info(user.toString());
        return user;
    }
}

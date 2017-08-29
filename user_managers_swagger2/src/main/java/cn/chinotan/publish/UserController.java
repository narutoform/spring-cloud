package cn.chinotan.publish;

import cn.chinotan.dao.UserDao;
import cn.chinotan.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "用户api", produces = "application/json",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        protocols = "http")
public class UserController {

    @Autowired
    UserDao userDao;

    Logger logger = Logger.getLogger(UserController.class);

    @GetMapping("/user/{id}")
    @ApiOperation(value = "get", notes = "根据id得到用户", httpMethod = "GET")
    @ApiImplicitParam(required = true, name = "id", dataType = "Long")
    public Object getOne(@PathVariable Long id) {
        User user = userDao.findOne(id);//区别于getOne,注意区别，getOne得到的是个代理

        logger.info(user.toString());
        return user;
    }
}

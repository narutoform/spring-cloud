package cn.chinotan.publish;

import cn.chinotan.entity.User;
import cn.chinotan.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    UserFeignClient userFeignClient;
    
    @GetMapping("/movie/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userFeignClient.getOne(id);

        return user;
    }

}

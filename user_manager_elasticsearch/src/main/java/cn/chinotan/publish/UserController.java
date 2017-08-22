package cn.chinotan.publish;

import cn.chinotan.entity.User;
import cn.chinotan.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    Logger logger = Logger.getLogger(UserController.class);
    
    @PostMapping("/user/index")
    public Long createUserToIndex(@RequestBody User user){
        Long aLong = userService.saveUser(user);
        
        return aLong;
    }

    @GetMapping("/user/search")
    public Object searchUserFromIndex(Integer page, Integer num, String searchString){
        // 非高亮
        // List<User> users = userService.searchCity(page, num, searchString);
        
        // 高亮
        List<User> users = userService.searchCityHighlight(page, num, searchString);
        return users;
    }

    @GetMapping("/user/likeName")
    public Object findByNameLike(String name){
        List<User> users = userService.findByNameLike(name);

        return users;
    }

    @GetMapping("/user/ageIn")
    public Object findByAgeIn(Integer[] a){
        List<User> users = userService.findByAgeIn(Arrays.asList(a));

        return users;
    }
}

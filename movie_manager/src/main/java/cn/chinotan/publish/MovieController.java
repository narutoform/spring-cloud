package cn.chinotan.publish;

import cn.chinotan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    RestTemplate template;

    @RequestMapping("/movie/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = template.getForObject("http://userAdmin/user/" + id, User.class);

        return user;
    }

}

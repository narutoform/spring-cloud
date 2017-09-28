package cn.chinotan.publish;

import cn.chinotan.entity.User;
import cn.chinotan.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    Logger logger = Logger.getLogger(UserController.class);

    @PostMapping("/user/index")
    public ResponseEntity createUserToIndex(@RequestBody User user) throws IOException {
        ResponseEntity entity = null;
        entity = userService.saveUser(user);

        return entity;
    }

    @GetMapping("/user/search")
    public Object searchUserFromIndex(Integer page, Integer num, String searchString) {
        List<Map<String, Object>> users = userService.searchName(page, num, searchString);
        return users;
    }

    @PutMapping("/user/update")
    public Object updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        ResponseEntity responseEntity = userService.updataUser(user);
        return responseEntity;
    }

    @DeleteMapping("/user/delete/{id}")
    public Object deleteUser(@PathVariable String id) throws Exception {
        ResponseEntity responseEntity = userService.deleteUser(id);
        return responseEntity;
    }
}

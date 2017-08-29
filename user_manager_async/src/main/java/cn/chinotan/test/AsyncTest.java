package cn.chinotan.test;

import cn.chinotan.entity.User;
import cn.chinotan.publish.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AsyncTest {
    
    @Autowired
    UserService userService;
    
    @Test
    public void testUservice(){
        try {
            Future<User> future = userService.getOne(1L);
            while (true){
                if (future.isDone()){
                    User user = future.get();
                    log.debug(user.toString());
                    break;
                }
                Thread.sleep(1000);
                log.debug("没完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

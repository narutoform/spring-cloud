package cn.chinotan.publish;

import cn.chinotan.dao.UserDao;
import cn.chinotan.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class UserService {
    
    @Autowired
    UserDao userDao;
    
    @Async
    public Future<User> getOne(Long id) throws InterruptedException {
        User user = userDao.findOne(id);
        log.debug(user.toString());
        
        Thread.sleep(5000);
        
        log.debug("异步执行");
        
        return new AsyncResult<User>(user);
    }
}

package cn.chinotan.publish;

import cn.chinotan.dao.UserDao;
import cn.chinotan.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserDao userDao;
    
    private static Integer a = 0;
    
    // value是在什么异常的情况下才重试，默认的话是全部异常都重试
    // 每次重试延迟毫秒数
    @Retryable(maxAttempts = 5, value = {InterruptedException.class,
            RetryException.class},
            backoff = @Backoff(value = 100))
    public User getOne(Long id) throws InterruptedException {
        User user = null;
        while (true){
            Thread.sleep(1000);
            if (a < 5){
                a++;
                log.error("retry第{}次", a);
                throw new RetryException("重试失败");
            }
            user = userDao.findOne(id);
            log.debug(user.toString());
            break;
        }
        
        return user;
    }
    
    // 在指定方法上标记@Recover来开启重试失败后调用的方法(注意,需跟重处理方法在同一个类中)
    // 参数是要处理的异常
    @Recover
    public void recover(RetryException e){
        log.error("recover重试失败");
    }
}

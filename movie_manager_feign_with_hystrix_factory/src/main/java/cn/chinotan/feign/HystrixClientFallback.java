package cn.chinotan.feign;

import cn.chinotan.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HystrixClientFallback implements FallbackFactory<UserFeignClient> {

    //Logger logger = Logger.getLogger(this.getClass().toString());
    private static final Logger LOGGER = Logger.getLogger("my : ");
    
    @Override
    public UserFeignClient create(Throwable throwable) {
        // throwable.getMessage().toString()绝对不能用，很严重，报空指针错误, message为空
        String message = throwable.getMessage();
        LOGGER.info(message);
        
        return new HystrixClientWithFallBackFactory() {
            @Override
            public User findById(Long id) {
                User user = new User();
                user.setId(-1L);
                user.setName("不存在");

                return user;
            }
        };
    }
}


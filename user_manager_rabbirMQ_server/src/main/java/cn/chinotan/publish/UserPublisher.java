package cn.chinotan.publish;

import cn.chinotan.dao.UserDao;
import cn.chinotan.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPublisher {
    
    @Autowired
    UserDao userDao;

    @Autowired
    AmqpTemplate template;

    private final static ObjectMapper mapper = new ObjectMapper();
    
    Logger logger = Logger.getLogger(UserPublisher.class);
    
    @GetMapping("/user/{id}")
    public Object getOne(@PathVariable Long id) throws JsonProcessingException {
        User user = userDao.findOne(id);

        logger.info(user.toString());
        
        // 改为json格式
        String asString = mapper.writeValueAsString(user);

        // 发送到消息队列 发送端的routing_key写任何字符都会被忽略
        template.convertAndSend("userExchange","", asString);
        
        return user;
    }
}

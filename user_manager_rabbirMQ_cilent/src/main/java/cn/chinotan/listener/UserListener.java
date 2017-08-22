package cn.chinotan.listener;

import cn.chinotan.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RabbitListener(queues = "userQueue2")
@Component
public class UserListener {

    private final static ObjectMapper mapper = new ObjectMapper();
    
    Logger logger = Logger.getLogger(UserListener.class);
    
    @RabbitHandler
    public void getUserFromRabbitMQ(String userString) throws IOException {
        User user = mapper.readValue(userString, User.class);

        logger.info(user.toString());
    }
}

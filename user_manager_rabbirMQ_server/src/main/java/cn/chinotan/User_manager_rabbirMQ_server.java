package cn.chinotan;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class User_manager_rabbirMQ_server {

    public static void main(String[] args) {
        SpringApplication.run(User_manager_rabbirMQ_server.class, args);
    }
    
    @Bean
    public Queue queue1(){
        return new Queue("userQueue1");
    }

    @Bean
    public Queue queue2(){
        return new Queue("userQueue2");
    }

    @Bean
    public Exchange exchange(){
        return new FanoutExchange("userExchange", true, true);
    }
    
    @Bean
    public Binding binding1(FanoutExchange exchange, Queue queue1){
        return BindingBuilder.bind(queue1).to(exchange);
    }

    @Bean
    public Binding binding2(FanoutExchange exchange, Queue queue2){
        return BindingBuilder.bind(queue2).to(exchange);
    }
}

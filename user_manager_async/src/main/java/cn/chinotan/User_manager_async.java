package cn.chinotan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableAsync
public class User_manager_async {

    public static void main(String[] args) {
        SpringApplication.run(User_manager_async.class, args);
    }
    
    public Executor executor(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        return executorService;
    }
    
}

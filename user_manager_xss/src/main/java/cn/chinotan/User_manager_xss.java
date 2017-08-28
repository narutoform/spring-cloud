package cn.chinotan;

import cn.chinotan.xss.XssStringJsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
@ServletComponentScan // 为了filter等组件能用得加这个或者Filter加个@@Component
public class User_manager_xss {

    public static void main(String[] args) {
        SpringApplication.run(User_manager_xss.class, args);
    }
    
    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(new XssStringJsonSerializer());
        objectMapper.registerModule(simpleModule);
        
        return objectMapper;
    }
    
}

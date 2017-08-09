package config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration1 {
    
    @Bean
    public Contract getBulider(){
        return new Contract.Default();
    }
    
    @Bean
    public Logger.Level getLogger(){
        return Logger.Level.FULL;
    }
}

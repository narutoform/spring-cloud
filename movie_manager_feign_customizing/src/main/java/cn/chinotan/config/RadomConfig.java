package cn.chinotan.config;

import cn.chinotan.annotation.RadomRibbon;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RadomRibbon
@Configuration
public class RadomConfig {
    
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
    
}

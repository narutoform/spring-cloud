package cn.chinotan.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cn.chinotan.cors")
@Validated
public class CorsConfig {

    @NotNull
    private Map<String, CorsRegistrationConfig> config;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                config.forEach((key, registrationConfig) -> {
                    CorsRegistration cr = registry.addMapping(registrationConfig.getMapping());

                    if (registrationConfig.getAllowCredentials() != null){
                        cr.allowCredentials(registrationConfig.getAllowCredentials());
                    }
                    if (StringUtils.isNotBlank(registrationConfig.getAllowedOrigins())){
                        cr.allowedOrigins(registrationConfig.getAllowedOrigins());
                    }
                    if (StringUtils.isNotBlank(registrationConfig.getAllowedHeaders())){
                        cr.allowedHeaders(registrationConfig.getAllowedHeaders());
                    }
                    if (StringUtils.isNotBlank(registrationConfig.getAllowedMethods())){
                        cr.allowedMethods(registrationConfig.getAllowedMethods());
                    }
                });
            }
        };
    }

    public static class CorsRegistrationConfig {

        //描述 : 扫描地址
        private String mapping = "/**";
        //描述 : 允许证书
        private Boolean allowCredentials = null;
        //描述 : 允许的域
        private String allowedOrigins = "*";
        //描述 : 允许的方法
        private String allowedMethods = "POST,GET,DELETE,PUT";
        //描述 : 允许的头信息
        private String allowedHeaders = "*";

        public String getMapping() {
            return mapping;
        }

        public void setMapping(String mapping) {
            this.mapping = mapping;
        }

        public Boolean getAllowCredentials() {
            return allowCredentials;
        }

        public void setAllowCredentials(Boolean allowCredentials) {
            this.allowCredentials = allowCredentials;
        }

        public String getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(String allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        public String getAllowedMethods() {
            return allowedMethods;
        }

        public void setAllowedMethods(String allowedMethods) {
            this.allowedMethods = allowedMethods;
        }

        public String getAllowedHeaders() {
            return allowedHeaders;
        }

        public void setAllowedHeaders(String allowedHeaders) {
            this.allowedHeaders = allowedHeaders;
        }
    }

    public Map<String, CorsRegistrationConfig> getConfig() {
        return config;
    }

    public void setConfig(Map<String, CorsRegistrationConfig> config) {
        this.config = config;
    }
}
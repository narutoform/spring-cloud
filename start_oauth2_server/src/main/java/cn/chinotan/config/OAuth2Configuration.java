package cn.chinotan.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("chinotan")
                .secret("chino")
                .scopes("openid")
                .authorizedGrantTypes("implicit", "password", "authorization_code", "client_credentials", "refresh_token")
                .authorities("READ", "WRITE")
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        List<UserDetails> list = new ArrayList<UserDetails>();
        
        list.add(new User("user", "user", Lists.newArrayList(new SimpleGrantedAuthority("READ"))));
        list.add(new User("admin", "admin", Lists.newArrayList(new SimpleGrantedAuthority("READ"), new SimpleGrantedAuthority("WRITE"))));
        
        endpoints.tokenStore(getJWTTokenStore())
                .tokenEnhancer(getJwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .userDetailsService(new InMemoryUserDetailsManager()); // 需要指定userDetailsService否则在请求refresh_token接口时报userDetailsService is required
    }

    private TokenStore getJWTTokenStore() {
        return new JwtTokenStore(getJwtAccessTokenConverter());
    }

    private JwtAccessTokenConverter getJwtAccessTokenConverter() {
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "mydearyui".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyFactory.getKeyPair("jwt"));

        return converter;
    }
}

package com.example.demo.config;

import com.example.demo.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


/**
 * @author: longzhonghua
 * @date: 2019/10/9
 * Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**Spring Boot版本2.0.*无法自动注入AuthenticationManager,需要重写authenticationManagerBean*/
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints.authenticationManager(authenticationManager)//使用oauth2的密码模式时需要配置authenticationManager
                .userDetailsService(userSecurityService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("app")
                //Spring Boot1.x版本中不需要加密的，但2.x版本需要加密
                .secret(passwordEncoder.encode("123456"))
                //这样写的话，获取的token里不会有refresh_token
                .authorizedGrantTypes("password")
                .scopes("all")
                //Token有效时间
                .accessTokenValiditySeconds(36000)
                .and()
                .withClient("web")
                .secret(passwordEncoder.encode("123456"))
                //获取的token里有refresh_token
                .authorizedGrantTypes("password", "refresh_token","authorization_code","client_credentials")
                .scopes("all")
                .redirectUris("http://www.longzhonghua.com")
                .accessTokenValiditySeconds(36000);
    }
}

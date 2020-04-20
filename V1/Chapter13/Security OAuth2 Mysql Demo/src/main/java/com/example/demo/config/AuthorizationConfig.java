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
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;


/**
 * @author: longzhonghua
 * @date: 2019/10/9
 * Description:
 */

@EnableAuthorizationServer
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    //在配置文件中定义数据库信息
    private DataSource dataSource;
    @Autowired
    private UserSecurityService userSecurityService;

    @Bean
    // 声明TokenStore实现
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    // 声明ClientDetails实现，使用JdbcClientDetailsService客户端详情服务
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    // 配置实现
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /*使用oauth2的密码模式时需要配置authenticationManager*/
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore())
                /* 配置tokenStore,需要配置userDetailsService，否则refresh_token会报错*/
                .userDetailsService(userSecurityService);

        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //从数据库查询Token
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
        endpoints.tokenServices(tokenServices);

    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //oauthServer.checkTokenAccess("isAuthenticated()");
        // 开启oauth、token_key验证端口无权限访问
        oauthServer.checkTokenAccess("permitAll()");
        oauthServer.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从JDBC查出数据，如客户端ID、密码、授权方式等
        clients.withClientDetails(clientDetails());
/*        clients.inMemory()
                .withClient("client")
                .secret("secret")
                .authorizedGrantTypes("authorization_code")
                .scopes("app");*/
    }

}
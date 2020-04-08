package com.example.demo.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.HashMap;


@Configuration
/*@EnableResourceServer*/
@EnableGlobalMethodSecurity(prePostEnabled=true)//判断用户对某个控制层的方法是否具有访问权限
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Configuration
    @EnableResourceServer
    public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().httpBasic().disable().exceptionHandling().authenticationEntryPoint((req, resp, exception) -> {
                resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                resp.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap() {{
                    put("status", 0);
                    put("error", "没有权限");
                }}));
            })
                    .and().authorizeRequests().antMatchers("/noauth").permitAll()
                    .and().authorizeRequests().anyRequest().authenticated();
        }
    }
}

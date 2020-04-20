package com.example.demo.config;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * 自定义配置Feign
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Contract feignContract() {
        /*  Spring Cloud Netflix默认的SpringMvcController将替换为feign.Contract.Default。
         *  用feign.Contract.Default将契约改为Feign原生的默认契约，就可以使用feign自带的注解了
         */
        return new feign.Contract.Default();
    }

}
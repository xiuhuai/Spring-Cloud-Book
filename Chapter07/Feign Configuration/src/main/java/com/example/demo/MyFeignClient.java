package com.example.demo;

import com.example.demo.config.FeignConfiguration;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "feignClient", name = "service-producer", configuration = FeignConfiguration.class)
public interface MyFeignClient {
    /**
    * Spring MVC注解修改为Feign自带的注解；
    * 使用feign自带的注解@RequestLine；
    */
    @RequestLine("GET /hello")
    public String hello();
}
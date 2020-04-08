package com.example.demo;

import com.example.demo.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 调用服务接口
 */
@FeignClient(contextId = "feignClient", name = "service-producer", configuration = FeignConfiguration.class)
public interface MyFeignClient {

    @GetMapping(value = "/hello")
    /**
     * 调用服务接口
     */
    public String hello();
}
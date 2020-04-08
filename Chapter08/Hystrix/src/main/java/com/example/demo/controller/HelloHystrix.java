package com.example.demo.controller;


import com.example.demo.MyFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 调用服务接口
 */
@Component
public class HelloHystrix implements MyFeignClient {
    @Override
    public String hello() {
        return "出现错误 ";
    }
}
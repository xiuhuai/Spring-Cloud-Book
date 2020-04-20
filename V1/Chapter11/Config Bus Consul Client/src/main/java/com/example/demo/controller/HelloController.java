package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * 否则客户端会受到服务端的更新消息，但是更新不了，因为不知道更新哪里的。
*/
@RefreshScope
class HelloController {

    @Value("${app.version}")
    private String version;  //    @Value注解来获取server端参数的值
    @Value("${server.port}")
    private String port;  //    @Value注解来获取server端参数的值
    @Value("${message}")
    private String message;  //    @Value注解来获取server端参数的值
    @RequestMapping("/hello")
    public String from() {
        String s="version："+this.version+" port："+this.port+message;
        return  s;
    }
}
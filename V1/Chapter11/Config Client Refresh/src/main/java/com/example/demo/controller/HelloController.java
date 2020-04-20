package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* *
 *开启配置刷新功能，当客户端执行 /actuator/refresh 的时候就会更新此类下面的变量值。
 *需要POST http://url:por/actuator/refresh才可以刷新，也可以利用GitHub的Webhook实现自动刷新。
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
        String s = "version：" + this.version + " port：" + this.port + message;
        return s;
    }
}
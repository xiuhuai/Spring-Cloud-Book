package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**服务接口*/
@RestController
public class HelloController {
    /*注入“服务提供者”的名称*/
    @Value("${provider.name}")
    private String name;
    /*注入“服务提供者”的端口号*/
    @Value("${server.port}")
    private String port;
    /*提供的接口，用于返回信息*/
    @RequestMapping("/hello")
    public String hello() {
        String str="provider:" + name + " port:" + port;
        //返回数据
        return str;
    }
}

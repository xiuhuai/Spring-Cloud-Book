package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: Ribbon的规则测试类
 */
@RestController
public class TestController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    Date date=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
    @GetMapping("/test")
    public void test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        System.out.println(serviceInstance.getHost() + serviceInstance.getPort()+" "+sdf.format(date));
    }


}

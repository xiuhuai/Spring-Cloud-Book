package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.rmi.server.LoaderHandler;

/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 实现客户端服务均衡，不适用注册中心，自己维护服务提供者清单。
 */
@RestController
public class HelloController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello() {
        return restTemplate.getForObject("http://provider/" + "hello", String.class);
    }

    @GetMapping("/LoadInstance")
    public String LoadInstance() {
        /* *
         * restTemplate.getForObject()与loadBalancerClient.choose不能放在一个方法中，因为restTemplate.getForObject()包含了choose方法
         *
         */
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        return serviceInstance.toString();
    }

}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/test")
    public void LoadInstance(){
        /*
        * restTemplate.getForObject()与loadBalancerClient.choose不能放在一个方法中，因为restTemplate.getForObject()包含了choose方法
        *
        * */
        ServiceInstance serviceInstance=loadBalancerClient.choose("producer");
         System.out.println(serviceInstance.getHost()+serviceInstance.getPort());
    }



}

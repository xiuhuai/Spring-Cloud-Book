package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 调用服务接口
 */
@RestController
public class HelloController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务提供者
     */
    @GetMapping("/instances-lists")
    public Object instancesLists() {
        return discoveryClient.getInstances("service-producer");
    }

    /**
     * 获取所有注册服务名称
     */
    @GetMapping("/services-lists")
    public Object servicesLists() {
        return discoveryClient.getServices();
    }


    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @GetMapping("/poll-service")
    public Object pollService() {
        return loadBalancer.choose("service-producer").getUri().toString();
    }

    /**
     * 调用服务提供者接口
     */
    @GetMapping("/hello")
    public String hello() {
        ServiceInstance serviceInstance = loadBalancer.choose("service-producer");
        URI uri = serviceInstance.getUri();
        String callService = new RestTemplate().getForObject(uri + "/hello", String.class);
        System.out.println(callService);
        return callService;
    }
}

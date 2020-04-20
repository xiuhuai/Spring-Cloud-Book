package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sun.rmi.server.LoaderHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;




    @GetMapping("/hello")
   public String hello(){
        UriComponents uriComponents= UriComponentsBuilder.fromUriString("http://Producer-Service/user?name={name}").build().expand("longzhonghua").encode();
        URI uri=uriComponents.toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.toString();
    }
    @GetMapping("/LoadInstance")
    public void LoadInstance(){
        /*
        * restTemplate.getForObject()与loadBalancerClient.choose不能放在一个方法中，因为restTemplate.getForObject()包含了choose方法
        *
        * */
        ServiceInstance serviceInstance=loadBalancerClient.choose("producer");
         System.out.println(serviceInstance.getHost()+serviceInstance.getPort());
    }



}

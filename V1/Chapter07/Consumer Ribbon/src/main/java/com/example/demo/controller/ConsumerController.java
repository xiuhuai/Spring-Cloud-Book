package com.example.demo.controller;

import com.example.demo.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    HelloRemote HelloRemote;
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {

            return HelloRemote.hello(name);

//        return restTemplate.getForEntity("http://producer/hello?name=123", String.class).getBody();


    }

}
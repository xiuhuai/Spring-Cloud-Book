package com.example.demo.controller;

import com.example.demo.HelloProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloProducer  helloProducer;

    @RequestMapping("/hello")
    public String index() {
        return helloProducer.hello();
    }

}
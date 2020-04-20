package com.example.demo.controller;

import com.example.demo.MyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MyFeignClient myFeignClient;

    @GetMapping("/hello")
    public String index() {
        return myFeignClient.hello();
    }

}
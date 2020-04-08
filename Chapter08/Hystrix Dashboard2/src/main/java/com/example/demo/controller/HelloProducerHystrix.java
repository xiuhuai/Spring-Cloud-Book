package com.example.demo.controller;

import com.example.demo.HelloProducerTwo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloProducerHystrix implements HelloProducerTwo {

    @Override
    public String hello() {
        return "出现错误";
    }
}
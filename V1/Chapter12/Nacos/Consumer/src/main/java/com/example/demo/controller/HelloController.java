package com.example.demo.controller;

import com.example.demo.service.MyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: longzhonghua
 * @date: 2019/10/3
 * Description:
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MyFeignClient myFeignClient;

    @GetMapping(value = "/hello-rest/{str}")
    public String rest(@PathVariable String str) {
        return restTemplate.getForObject("http://Producer/hello/" + str, String.class);
    }
    @GetMapping(value = "/hello-feign/{str}")
    public String feign(@PathVariable String str) {
        return myFeignClient.echo(str);
    }
}
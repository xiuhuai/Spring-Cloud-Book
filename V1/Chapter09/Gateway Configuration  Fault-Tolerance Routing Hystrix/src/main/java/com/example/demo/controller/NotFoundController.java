package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: longzhonghua
 * @date: 2019/9/25
 * Description: 路由容错控制器，当Hystrix容错时，根据配置来调用此方法，用来返回信息，
 */
@RestController
public class NotFoundController {
    @RequestMapping(value = "/fallback")
    public Mono<Map<String, String>> notFound() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("code", "100");
        stringMap.put("data", "Service Not Available");
        return Mono.just(stringMap);
    }
}

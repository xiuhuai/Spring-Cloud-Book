package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: longzhonghua
 * @date: 2019/9/25
 * Description: 路由容错控制器，当没匹配到路由时使用，用来返回信息，
 */
@RestController
public class NotFoundController {
    @RequestMapping(value = "/notfound")
    public Mono<Map<String, String>> notFound() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("code", "404");
        stringMap.put("data", "found");
        return Mono.just(stringMap);
    }
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 查询网关的路由信息
 */
@RestController
public class RouteController {

    @Autowired
    private RouteDefinitionLocator routeDefinitionLocator;

    //获取网关所有的路由信息
    @RequestMapping("/routes")
    public Flux<RouteDefinition> getRouteDefinitions(){
        return routeDefinitionLocator.getRouteDefinitions();
    }
}

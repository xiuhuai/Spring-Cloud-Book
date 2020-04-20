package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Mapper.GatewayRoutesMapper;
import com.example.demo.config.RedisConfig;
import com.example.demo.entity.Routes;
import com.example.demo.service.RoutesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/routes")
public class RoutesController {
    @Resource
    private RoutesService routesService;
    @Resource
    private GatewayRoutesMapper gatewayRoutesMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**获取所有动态路由信息*/
    @RequestMapping("/")
    public String synRouteDefinitions() {
        /*从redis中获取路由信息*/
        String result = stringRedisTemplate.opsForValue().get(RedisConfig.routeKey);
        if (!StringUtils.isEmpty(result)) {
       //返回Redis 中的路由信息
        } else {
            //返回MySQL中的路由信息
            result = JSON.toJSONString(routesService.getRouteDefinitions());
            //把路由信息存储到Redis
            stringRedisTemplate.opsForValue().set(RedisConfig.routeKey, result);
        }
        log.info("路由信息：" + result);
        return result;
    }
    //添加路由信息
    @PostMapping(value = "/add")
    public String add(@RequestBody Routes route) {
        return gatewayRoutesMapper.add(route) > 0 ? "success" : "fail";
    }
}

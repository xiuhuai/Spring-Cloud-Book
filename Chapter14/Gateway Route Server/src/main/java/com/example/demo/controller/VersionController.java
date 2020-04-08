package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.Mapper.GatewayRoutesMapper;
import com.example.demo.Mapper.VersionMapper;
import com.example.demo.config.RedisConfig;


import com.example.demo.entity.GatewayDefinition.GatewayRouteDefinition;
import com.example.demo.entity.Version;
import com.example.demo.service.RoutesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/version")
public class VersionController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private VersionMapper versionMapper;
    @Resource
    private GatewayRoutesMapper gatewayRoutesMapper;
    @Resource
    private RoutesService routesService;
    //获取最后一次发布的版本号
    @GetMapping(value = "/lastVersion")
    public Long getLastVersion() {
        Long versionId = 0L;
        String result = redisTemplate.opsForValue().get(RedisConfig.versionKey);
        if (!StringUtils.isEmpty(result)) {
            log.info("返回Redis 中的版本信息......");
            versionId = Long.valueOf(result);
        } else {
            log.info("返回mysql中的版本信息......");
            versionId = versionMapper.getLastVersion();
            redisTemplate.opsForValue().set(RedisConfig.versionKey, String.valueOf(versionId));
        }
        return versionId;
    }


}

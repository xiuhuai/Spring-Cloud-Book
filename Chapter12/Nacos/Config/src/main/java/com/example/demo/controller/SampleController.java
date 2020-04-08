package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;

/**
 * @author: longzhonghua
 * @date: 2019/9/27
 * Description:
 */
@RestController
//添加此注解，配置信息可以及时刷新
@RefreshScope
class SampleController {

    @Value("${mysql.address}")
    String mysqlAddress;
    @Value("${mysql.port}")
    String mysqlPort;
    @RequestMapping("/getProperties")
    public String get() {
        return mysqlAddress + mysqlPort;
    }
}

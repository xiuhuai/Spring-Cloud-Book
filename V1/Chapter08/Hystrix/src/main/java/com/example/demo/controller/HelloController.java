package com.example.demo.controller;

import com.example.demo.MyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: 调用服务接口
 */
@RestController
public class HelloController {

    @Autowired
    MyFeignClient myFeignClient;

    @RequestMapping("/hello")
    public String index() {
        return myFeignClient.hello();
    }

}
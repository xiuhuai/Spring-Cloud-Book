package com.example.demo.controller;

import com.example.demo.service.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: longzhonghua
 * @date: 2019/10/3
 * Description:测试关联模式
 */
@RestController
@RequestMapping("/chain")
public class ChainController {
    @Autowired
    private ChainService chainService;

    @GetMapping("a")
    public String flowRelatedA() {
        chainService.chainTest();
        System.out.println("接口a");
        return "接口a";
    }

    @GetMapping("b")
    public String flowRelatedB() {
        chainService.chainTest();
        System.out.println("接口b");
        return "接口b";
    }

}

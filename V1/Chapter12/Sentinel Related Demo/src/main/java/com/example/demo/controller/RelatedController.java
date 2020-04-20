package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: longzhonghua
 * @date: 2019/10/3
 * Description:测试关联模式
 */
@RestController
@RequestMapping("/related")
public class RelatedController {
    @GetMapping("a")
    public String flowRelatedA() {
        System.out.println("a接口");
        return "a接口";
    }
    @GetMapping("b")
    public String flowRelatedB() {
        System.out.println("b接口");
        return "b接口";
    }
}

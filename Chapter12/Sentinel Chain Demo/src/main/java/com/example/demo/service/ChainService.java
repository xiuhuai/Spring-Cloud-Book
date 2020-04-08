package com.example.demo.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author: longzhonghua
 * @date: 2019/10/3
 * Description:
 */
@Service
public class ChainService {
    @SentinelResource
    public void chainTest()
    {
        System.out.println("测试链路情况");
    }
}
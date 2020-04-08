package com.example.demo.controller;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class GetTest {

    @Autowired
    RestTemplate restTemplate;
    //返回String,不带参数
    @Test
    public void nparameters() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://Producer-Service/hello", String.class);
        System.out.println(responseEntity.getBody());
    }
    @Test
    public void withparameters1() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/getparameter?name={1}&id={2}", String.class, "hua",2);
        System.out.println(responseEntity.getBody());
    }
    //返回String,带参数
    @Test
    public void withparameters2() {

        Map<String, String> map = new HashMap<>();
        map.put("name", "zhonghuaLong");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/getparameter?name={name}&id=3", String.class, map);
        System.out.println(responseEntity.getBody());
    }
    @Test
    public void restUser1() {

        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://localhost:8080/getuser1", User.class);
        System.out.println(responseEntity.getBody().getId());
        System.out.println(responseEntity.getBody().getName());
    }
    @Test
    public void  getForObject() {

        User user = restTemplate.getForObject("http://localhost:8080/getuser1", User.class);
        System.out.println(user.getName());
    }

}
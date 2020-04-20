package com.example.demo.controller;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

/**
 * @author: longzhonghua
 * @date: 2019/10/10
 * Description:
 */
public class UserControllerTest {

    @Test
    public void register() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println( encoder.encode("longzhonghua"));
    }
}
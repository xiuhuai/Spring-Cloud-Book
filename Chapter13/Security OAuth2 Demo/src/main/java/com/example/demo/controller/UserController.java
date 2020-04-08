package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: longzhonghua
 * @date: 2019/10/9
 * Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<User> userList() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/testauthority")
    public String testauthority() {
        return "admin权限可以查看";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/testrole")
    public String testrole() {
        return "角色ROLE_admin可以查看";
    }

    @GetMapping("/register")
    public ResponseEntity<ResponseData> register(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(new ResponseData(userRepository.save(user)));
    }
}


package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }



}

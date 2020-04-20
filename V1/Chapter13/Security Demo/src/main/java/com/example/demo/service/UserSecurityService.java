package com.example.demo.service;



import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

//@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
         User user = userRepository.findByUsername(name);
        if (user == null) {

            throw new UsernameNotFoundException("用户名不存在");

        }
        return user;
    }
}

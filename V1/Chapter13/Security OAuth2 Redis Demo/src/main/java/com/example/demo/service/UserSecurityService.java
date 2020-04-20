package com.example.demo.service;



import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */


@Component
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
         User user = userRepository.findByUsername(name);
/*        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        else*/
            if(user != null){
/*            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if ("hua".equals(user.getUsername())) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_admin"));//只有xiekun这个用户才有管理员权限，这个地方必须要加ROLE_
            }
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_normal"));
//            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);*/
                System.out.println(user);
            return user;

        }
        throw new UsernameNotFoundException("用户名不存在");
    }
}

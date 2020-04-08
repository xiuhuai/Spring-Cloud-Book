package com.example.demo.entity;
/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类，通过实现UserDetails接口实现认证及授权
 */
@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}


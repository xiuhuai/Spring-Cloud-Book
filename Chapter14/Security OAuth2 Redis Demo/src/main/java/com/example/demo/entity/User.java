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
import java.util.Collections;
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
    //多对多映射，用户角色
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<UserRole> roles;
    //根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
        return authorities;
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


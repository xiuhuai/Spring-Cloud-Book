package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: longzhonghua
 * @date: 2019/10/9
 * Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
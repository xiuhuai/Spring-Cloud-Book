package com.example.demo.repository;



import com.example.demo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
	UserRole findByName(String name);
}

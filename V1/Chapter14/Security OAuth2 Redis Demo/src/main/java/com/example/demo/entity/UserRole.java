package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */

@Entity
@Data
public class UserRole  implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String role;
    private String name;

}

package com.zy.model.user;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zy
 * @date 2020/3/28 19:56
 */
/*
* User实体类，对应数据库中的t_user表
* */
@Entity
@Data
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "nick_Name")
    private String nickName;

    @Column(name = "power")
    private String power;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "email")
    private String email;
}

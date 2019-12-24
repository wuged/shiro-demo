package com.proj.shirodemo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

/**
 * 用户
 * @author wuge
 * @date 2019/12/19
 */
@Data
@Table(name = "sys_user")
public class User {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 密码
     */
    @Column(name = "salt")
    private String salt;


    /**
     * 状态（0：不可用 1：可用）
     */
    @Column(name = "state")
    private String state;

    /**
     * 角色列表
     */
    private Set<Role> roles;
}

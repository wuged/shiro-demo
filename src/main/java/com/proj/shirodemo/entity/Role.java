package com.proj.shirodemo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

/**
 * 角色
 * @author wuge
 * @date 2019/12/19
 */
@Data
@Table(name = "sys_role")
public class Role {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 权限列表
     */
    private Set<Perm> perms;
}

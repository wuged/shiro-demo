package com.proj.shirodemo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限
 * @author wuge
 * @date 2019/12/19
 */
@Data
@Table(name = "sys_perm")
public class Perm {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 权限名
     */
    @Column(name = "permissions_name")
    private String permissionsName;

}

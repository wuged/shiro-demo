package com.proj.shirodemo.controller;

import com.proj.shirodemo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需要授权访问的地址
 * @author wuge
 * @date 2019/12/24
 */
@RestController
@RequestMapping("/authc")
public class AuthcController {

    @GetMapping("index")
    public Object index() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        return user.toString();
    }

    /**
     * shiroConfig配置需要管理员角色
     * @return
     */
    @GetMapping("admin")
    public Object admin() {
        return "Welcome Admin";
    }

    /**
     * shiroConfig配置需要权限user:delete
     * @return
     */
    @GetMapping("removable")
    public Object removable() {
        return "removable，需要user:delete权限";
    }

    /**
     * shiroConfig配置需要权限user:delete
     * @return
     */
    @RequiresPermissions(value = {"user:query"})
    @GetMapping("query")
    public Object query() {
        return "query，需要user:query权限";
    }

    /**
     * shiroConfig配置需要权限user:add和user:update
     * @return
     */
    @GetMapping("renewable")
    public Object renewable() {
        return "renewable，需要权限user:add和user:update";
    }
}

package com.proj.shirodemo.controller;

import com.proj.shirodemo.entity.User;
import com.proj.shirodemo.service.UserService;
import com.proj.shirodemo.util.PasswordHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户Controller
 * @author wuge
 * @date 2019/12/19
 */
@Slf4j
@RestController
@RequestMapping
public class UserController extends BaseController {

    /**
     * 注入UserService
     */
    @Resource
    private UserService userService;

    /**
     * 登录成功页
     * @return
     */
    @GetMapping("/home")
    public Object login() {
        return "Here is home page, login success!";
    }

    @GetMapping("/unauthc")
    public Object unauthc() {
        return "Here is Unauthc page";
    }
    
    @PostMapping("/login")
    public String dologin(@RequestBody User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            log.info("是否登录成功：" +subject.isAuthenticated());
        } catch (IncorrectCredentialsException ice) {
            return "password error!";
        } catch (UnknownAccountException uae) {
            return "username error!";
        }
        User selectUser = userService.selectByUserName(user.getUserName());
        log.info("登录成功，当前用户：" +selectUser);
        subject.getSession().setAttribute("user", selectUser);
        return "SUCCESS";
    }

    /**
     *  添加用户
     * @param username
     * @param password
     * @param roleId（1-admin， 2-user）角色
     * @return
     */
    @RequiresPermissions(value = {"user:add"})
    @GetMapping("/register")
    public Object register(@RequestParam String username, @RequestParam String password,
                Integer roleId) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        PasswordHelper.encryptPassword(user);
        user.setState("1");
        userService.saveUser(user, roleId);
        return "SUCCESS";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "logout";
    }
}

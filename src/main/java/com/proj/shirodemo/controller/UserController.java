package com.proj.shirodemo.controller;

import com.proj.shirodemo.entity.ResponseEntity;
import com.proj.shirodemo.entity.User;
import com.proj.shirodemo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户Controller
 * @author wuge
 * @date 2019/12/19
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 注入UserService
     */
    @Resource
    private UserService userService;
    
    @GetMapping("/login")
    public ResponseEntity login() {
        User zhangsan = userService.selectByUserName("wuge");
        return successResult(zhangsan);
    }
}

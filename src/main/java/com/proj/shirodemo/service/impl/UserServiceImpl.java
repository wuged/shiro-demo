package com.proj.shirodemo.service.impl;

import com.proj.shirodemo.dao.UserMapper;
import com.proj.shirodemo.entity.User;
import com.proj.shirodemo.service.UserService;
import com.proj.shirodemo.util.DataUtil;
import org.apache.shiro.authc.AccountException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户服务实现类
 * @author wuge
 * @date 2019/12/19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    /**
     * 注入UserMapper
     */
    @Resource
    private UserMapper userMapper;

    @Override
    public User selectByUserName(String userName) {
        if (DataUtil.isEmpty(userName)) {
            return null;
        }
        return userMapper.selectByUserName(userName);
    }

    @Override
    public void saveUser(User user, Integer roleId) {
        // 保存用户
        Long id = userMapper.save(user);
        // 保存用户权限
        if (DataUtil.isEmpty(roleId)) {
            // 抛出异常
            // throw new ServiceException("权限ID不能为空");
            throw new AccountException("权限ID不能为空");
        }
        userMapper.saveUserRole(id, Long.valueOf(roleId.toString()));
    }
}

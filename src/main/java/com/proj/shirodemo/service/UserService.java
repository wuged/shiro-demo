package com.proj.shirodemo.service;

import com.proj.shirodemo.entity.User;

/**
 * 用户服务层
 * @author wuge
 * @date 2019/12/19
 */
public interface UserService {

    /**
     *  根据用户名获取用户对象
     * @param userName
     * @return
     */
    User selectByUserName(String userName);

    /**
     * 新增用户并赋权
     * @param user
     * @param roleId
     */
    void saveUser(User user, Integer roleId);
}

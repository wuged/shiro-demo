package com.proj.shirodemo.dao;

import com.proj.shirodemo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户dao
 * @author wuge
 * @date 2019/12/19
 */
public interface UserMapper {

    /**
     *  根据用户名获取用户对象
     * @param userName
     * @return
     */
    User selectByUserName(@Param("userName") String userName);
}

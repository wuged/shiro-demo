package com.proj.shirodemo.realm;

import com.proj.shirodemo.entity.Perm;
import com.proj.shirodemo.entity.Role;
import com.proj.shirodemo.entity.User;
import com.proj.shirodemo.service.UserService;
import com.proj.shirodemo.util.DataUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 自定义Realm
 *
 * @author wuge
 * @date 2019/12/19
 */
public class CustomRealm extends AuthorizingRealm {

    /**
     * 注入UserService
     */
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.selectByUserName(userName);
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        if (DataUtil.isNotEmpty(user)) {
            for (Role role : user.getRoles()) {
                simpleAuthenticationInfo.addRole(role.getRoleName());
                for (Perm perm : role.getPerms()) {
                    simpleAuthenticationInfo.addStringPermission(perm.getPermissionsName());
                }
            }
        } else {
            throw new ArithmeticException("用户不存在");
        }
        return simpleAuthenticationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这一步的目的：POST请求会先走这个方法进行认证，然后进行请求
        // 判断用户名是否为空
        if (DataUtil.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        String userName = authenticationToken.getPrincipal().toString();
        User user = userService.selectByUserName(userName);
        if (DataUtil.isEmpty(user)) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                // ByteSource.Util.bytes(user.getSalt()) 二进制， getName() 获取realm名字
                user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return simpleAuthenticationInfo;
    }
}

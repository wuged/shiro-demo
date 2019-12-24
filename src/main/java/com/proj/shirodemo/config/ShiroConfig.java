package com.proj.shirodemo.config;

import com.google.common.collect.Maps;
import com.proj.shirodemo.realm.CustomRealm;
import com.proj.shirodemo.util.PasswordHelper;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Shiro配置类
 * @author wuge
 * @date 2019/12/24
 */
@Configuration
public class ShiroConfig {

    /**
     * 下面两个方法对 注解权限起作用有很大的关系，请把这两个方法，放在配置的最上面
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置登录、成功等url
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/home");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthc");

        /**
         * 过滤器
         * authc：所有已登陆用户可访问
         * roles：有指定角色的用户可访问，通过[ ]指定具体角色，这里的角色名称与数据库中配置一致
         * perms：有指定权限的用户可访问，通过[ ]指定具体权限，这里的权限名称与数据库中配置一致
         * anon： 所有用户可访问，通常作为指定页面的静态资源时使用
         */
        Map<String, String> filterChainDefinitionMap = Maps.newHashMap();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");

//        filterChainDefinitionMap.put("/authc/admin", "roles[admin]");
//        filterChainDefinitionMap.put("/authc/renewable", "perms[user:update, user:add]");
//        filterChainDefinitionMap.put("/authc/removable", "perms[user:delete]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 设置散列算法（md5）和散列次数（2）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITH_NAME);
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    /**
     * CustomRealm设置密码加密匹配器
     * 作用：
     * 进入CustomRealm认证的密码并没有看到加密的代码，原来在这里
     * @return
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * SecurityManager配置shiroRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * 配置shiro session 的一个管理器
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        return sessionManager;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /*@Bean
    public PasswordHelper passwordHelper() {
        return new PasswordHelper();
    }*/
}

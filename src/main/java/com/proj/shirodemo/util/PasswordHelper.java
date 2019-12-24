package com.proj.shirodemo.util;

import com.proj.shirodemo.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码加密
 * @author wuge
 * @date 2019/12/24
 */
public class PasswordHelper {

    /**
     * 随机构造器
     */
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /**
     * 基础散列算法
     */
    public static String ALGORITH_NAME = "md5";

    /**
     * 自定义散列次数
     */
    public static Integer HASH_ITERATIONS = 2;

    public static void encryptPassword(User user) {
        // 随机字符串作为salt因子，实际参与运算的salt我们还引入其它干扰因子
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        // SimpleHash(算法， 密码， 二进制的盐， 散列次数)
        String pwd = new SimpleHash(ALGORITH_NAME, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), HASH_ITERATIONS).toHex();

        // 设置新密码
        user.setPassword(pwd);
    }
}

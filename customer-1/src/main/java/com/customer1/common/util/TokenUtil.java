package com.customer1.common.util;

import org.springframework.util.DigestUtils;

import com.basic.common.constants.BusiConstants;

/**
 * 生成token的工具类
 *
 * @author
 * @date 2018/12/4 9:46
 **/
public class TokenUtil {
    /**
     * 根据手机号生成token
     *
     * @param mobile
     * @return
     */
    public static String genToken(String mobile) {
        long timestamp = System.currentTimeMillis();
        String genKey = "";
        genKey = mobile + timestamp;
        String token = DigestUtils.md5DigestAsHex(genKey.getBytes());
        token = BusiConstants.TOKEN_KEY_PREFIX + mobile + token;
        return token;
    }

    public static void main(String[] args) {
        //String s = genToken("13458562737");
        System.out.println(DigestUtils.md5DigestAsHex("13458562737".getBytes()));
    }
}

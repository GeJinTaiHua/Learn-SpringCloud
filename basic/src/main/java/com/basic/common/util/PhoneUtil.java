package com.basic.common.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lichao
 * @date 2018/11/12
 */
public class PhoneUtil {
    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean getMobile(String str) {
        Pattern pattern = null;
        Matcher matcher = null;
        boolean flag = false;
        // 验证手机号
        pattern = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
        matcher = pattern.matcher(str);
        flag = matcher.matches();
        return flag;
    }
    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m  = null;
        boolean b  = false;
        // 验证带区号的
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
        // 验证没有区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

}

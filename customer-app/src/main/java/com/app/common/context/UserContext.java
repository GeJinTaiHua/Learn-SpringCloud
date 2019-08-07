package com.app.common.context;

import com.app.domain.UserInfo;


/**
 * 用户上下文信息
 *
 * @author
 * @date 2018/12/28 9:56
 **/
public class UserContext {
    private static final ThreadLocal<UserInfo> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    public static UserInfo get() {
        if (USER_INFO_THREAD_LOCAL.get() == null) {
            return new UserInfo();
        }
        return USER_INFO_THREAD_LOCAL.get();
    }

    public static void set(UserInfo userInfo) {
        USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    /**
     * 回收线程
     */
    public static void remove() {
        USER_INFO_THREAD_LOCAL.remove();
    }
}

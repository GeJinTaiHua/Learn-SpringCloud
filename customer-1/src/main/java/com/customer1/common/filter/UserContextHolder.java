package com.customer1.common.filter;

import org.springframework.util.Assert;

/**
 * 用户上下文信息
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = userContext.get();

        if (context == null) {
            userContext.set(new UserContext());
        }

        return userContext.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    public static void clear() {
        userContext.remove();
    }
}

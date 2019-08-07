package com.basic.common.util;


/**
 * traceId工具类
 *
 * @author
 * @date 2018/11/28 17:53
 **/
public class TraceIdUtil {
    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    public static String getTraceId() {
        if (TRACE_ID.get() == null) {
            String s = UuidUntil.getUuid();
            setTraceId(s);
        }
        return TRACE_ID.get();
    }

    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }
}

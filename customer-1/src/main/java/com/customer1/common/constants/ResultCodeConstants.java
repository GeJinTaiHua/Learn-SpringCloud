package com.customer1.common.constants;

import java.util.HashMap;
import java.util.Map;

public class ResultCodeConstants {
    private static final Map<String, String> ERRORMSG_MAP = new HashMap<String, String>();

    /**
     * 10000--成功
     */
    public static final String RESULT_CODE_SUCESS = "10000";
    /**
     * 20000--失败
     */
    public static final String RESULT_CODE_FAIL = "20000";

    static {
        ERRORMSG_MAP.put(RESULT_CODE_SUCESS, "成功");
        ERRORMSG_MAP.put(RESULT_CODE_FAIL, "失败");
    }

    public static String getMsg(String code) {
        return ERRORMSG_MAP.get(code);
    }

    public static String getMsg(String code, String defaultMsg) {
        return ERRORMSG_MAP.containsKey(code) ? ERRORMSG_MAP.get(code) : defaultMsg;
    }
}

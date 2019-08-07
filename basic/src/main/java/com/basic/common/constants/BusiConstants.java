package com.basic.common.constants;


/**
 * 业务常量
 **/
public class BusiConstants {


    //--------------------------业务常量--------------------------
    /**
     * 行政区划编码长度
     */
    public static final int REGION_CODE_LENGTH = 6;

    /**
     * 设备信息关键字
     */
    public static final String CLIENT_MESS = "clientMess";

    /**
     * md5接口加密验签通过
     */
    public static final String YAN_QIAN_TONG_GUO = "10000";

    /**
     * md5接口加密验签失败
     */
    public static final String YAN_QIAN_SHI_BAI = "20000";

    //--------------------------前缀--------------------------
    /**
     * redis缓存前缀-token生成前缀
     */
    public static final String TOKEN_KEY_PREFIX = "token_app_";
}

package com.basic.common.constants;

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
    /**
     * 40000--查无数据
     */
    public static final String RESULT_GRPC_NODATA = "40000";
    /**
     * 50000--修改密码不一致
     */
    public static final String LOGIN_UPDATE_PASS = "50000";
    /**
     * 60000--用户无对应商户信息
     */
    public static final String RESULT_CODE_Merchant = "60000";
    /**
     * 70000--用户登录失败
     */
    public static final String RESULT_CODE_ALIFAIL = "70000";
    /**
     * 30000--无数据
     */
    public static final String RESULT_CODE_NODATA = "30000";

    /**
     * S00001--请勿非法提交数据
     */
    public static final String CODE_S0001 = "S0001";
    /**
     * S00002--调用接口异常--接口发生内部异常，请稍候再试
     */
    public static final String CODE_S0002 = "S0002";
    /**
     * S00003--未知异常--系统发生内部异常，请稍候再试
     */
    public static final String CODE_S0003 = "S0003";
    /**
     * S00004--数据连接或网络异常--数据连接或网络异常
     */
    public static final String CODE_S0004 = "S0004";
    /**
     * S00005--访问验证异常--接口访问验证异常
     */
    public static final String CODE_S0005 = "S0005";
    /**
     * S00006--参数异常--您提交的参数异常
     */
    public static final String CODE_S0006 = "S0006";
    /**
     * S00007--接口IO异常--接口IO关闭异常
     */
    public static final String CODE_S0007 = "S0007";
    /**
     * S00007--业务处理异常--未查询到数据结果
     */
    public static final String CODE_S0008 = "S0008";
    /**
     * S00009--短信验证码不一致
     */
    public static final String CODE_S0009 = "S0009";
    /**
     * S00010--额度测评不合格
     */
    public static final String CODE_S0010 = "S0010";
    /**
     * S00011--支付宝未进行实名认证
     */
    public static final String CODE_S0011 = "S0011";
    /**
     * S00012--短信验证码过期
     */
    public static final String CODE_S0012 = "S0012";
    /**
     * S00013--用户位置信息不能为空
     */
    public static final String CODE_S0013 = "S0013";
    /**
     * S00014--用户名或密码错误
     */
    public static final String CODE_S0014 = "S0014";
    /**
     * S00015--用户不存在
     */
    public static final String CODE_S0015 = "S0015";
    /**
     * S00016-- 短信发送错误
     */
    public static final String CODE_S0016 = "S0016";
    /**
     * S00017-- 用户已注册
     */
    public static final String CODE_S0017 = "S0017";
    /**
     * S00018-- token过期
     */
    public static final String CODE_S0018 = "S0018";
    /**
     * S00019-- 参数不全
     */
    public static final String CODE_S0019 = "S0019";
    /**
     * S00020-- 短信发送失败
     */
    public static final String CODE_S0020 = "S0020";
    /**
     * S00021-- 非法的手机号
     */
    public static final String CODE_S0021 = "S0021";
    /**
     * S00022-- 请完善认证信息
     */
    public static final String CODE_S0022 = "S0022";
    /**
     * S00023-- 订单不存在
     */
    public static final String CODE_S0023 = "S0023";
    /**
     * S00024-- 有未完成订单
     */
    public static final String CODE_S0024 = "S0024";
    /**
     * S00025-- 重复提交
     */
    public static final String CODE_S0025 = "S0025";
    /**
     * S00026-- 验证码错误
     */
    public static final String CODE_S0026 = "S0026";
    /**
     * S00027-- 未找到相关车辆信息
     */
    public static final String CODE_S0027 = "S0027";
    /**
     * S00028-- 该身份证已被认证
     */
    public static final String CODE_S0028 = "S0028";
    /**
     * S00029-- 签名不能为空
     */
    public static final String CODE_S0029 = "S0029";

    static {
        ERRORMSG_MAP.put(RESULT_CODE_SUCESS, "成功");
        ERRORMSG_MAP.put(RESULT_CODE_FAIL, "失败");
        ERRORMSG_MAP.put(RESULT_GRPC_NODATA, "查无数据");
        ERRORMSG_MAP.put(LOGIN_UPDATE_PASS, "旧密码不正确");
        ERRORMSG_MAP.put(RESULT_CODE_Merchant, "用户无对应商户信息");
        ERRORMSG_MAP.put(RESULT_CODE_ALIFAIL, "用户登录失败");

        ERRORMSG_MAP.put(CODE_S0001, "请勿非法提交数据");
        ERRORMSG_MAP.put(CODE_S0002, "接口发生内部异常，请稍候再试");
        ERRORMSG_MAP.put(CODE_S0003, "加载失败，请再试试吧！");
        ERRORMSG_MAP.put(CODE_S0004, "数据连接或网络异常");
        ERRORMSG_MAP.put(CODE_S0005, "接口访问验证异常");
        ERRORMSG_MAP.put(CODE_S0006, "您提交的参数异常");
        ERRORMSG_MAP.put(CODE_S0007, "接口IO关闭异常");
        ERRORMSG_MAP.put(CODE_S0008, "未查询到数据结果");
        ERRORMSG_MAP.put(CODE_S0009, "短信验证码不一致");
        ERRORMSG_MAP.put(CODE_S0010, "很抱歉，您目前暂时不在开发人群中，可以尝试更换支付宝账号后重试");
        ERRORMSG_MAP.put(CODE_S0011, "支付宝未进行实名认证");
        ERRORMSG_MAP.put(CODE_S0012, "短信验证码过期，请重新获取");
        ERRORMSG_MAP.put(CODE_S0013, "用户位置信息不能为空");
        ERRORMSG_MAP.put(CODE_S0014, "用户名或密码错误");
        ERRORMSG_MAP.put(CODE_S0015, "用户未注册，请先注册用户");
        ERRORMSG_MAP.put(CODE_S0017, "用户已注册");
        ERRORMSG_MAP.put(CODE_S0018, "请重新登录");
        ERRORMSG_MAP.put(CODE_S0019, "参数不全");
        ERRORMSG_MAP.put(CODE_S0020, "短信发送失败");
        ERRORMSG_MAP.put(CODE_S0021, "请输入正确的手机号码");
        ERRORMSG_MAP.put(CODE_S0022, "请完善认证信息");
        ERRORMSG_MAP.put(CODE_S0023, "订单不存在");
        ERRORMSG_MAP.put(CODE_S0024, "有未完成订单");
        ERRORMSG_MAP.put(CODE_S0025, "请忽重复提交");
        ERRORMSG_MAP.put(CODE_S0026, "验证码有误");
        ERRORMSG_MAP.put(CODE_S0027, "未找到相关车辆信息");
        ERRORMSG_MAP.put(CODE_S0028, "该身份证已经完成认证，不能重复认证！");
        ERRORMSG_MAP.put(CODE_S0029, "签名不能为空");

    }

    public static String getMsg(String code) {
        return ERRORMSG_MAP.get(code);
    }

    public static String getMsg(String code, String defaultMsg) {
        return ERRORMSG_MAP.containsKey(code) ? ERRORMSG_MAP.get(code) : defaultMsg;
    }
}

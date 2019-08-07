package com.app.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basic.common.constants.BusiConstants;
import com.basic.common.util.StringUtil;
import org.springframework.util.StringUtils;

import cn.hutool.http.HttpRequest;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.Result;
import com.app.common.constants.ResultCodeConstants;
import com.app.common.exception.AssertException;


/**
 * 公共工具方法
 *
 * @author
 * @date 2018/12/7 16:43
 **/
public class CommonUtil {
    /**
     * 前端展示list转换
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Map<String, List<T>> listToMapShow(List<T> list) {
        Map<String, List<T>> map = new HashMap<>(1);
        map.put("dataList", list);
        return map;
    }

    /**
     * 前端展示对象转换
     *
     * @param key map键
     * @param t   对象
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> objectToMapShow(String key, T t) {
        Map<String, T> map = new HashMap<>(1);
        map.put(key, t);
        return map;
    }

    /**
     * 参数校验结果处理
     *
     * @param result
     */
    public static void paramExceotion(Result result) {
        if (result == null || !result.isSuccess()) {
            throw new AssertException(ResultCodeConstants.CODE_S0006);
        }
    }
    public static void paramExceotionByResult(Result result) {
        if (result == null) {
            throw new AssertException(ResultCodeConstants.CODE_S0006);
        } else if (!result.isSuccess()) {
            List<String> errors = result.getErrors();
            //获取最后一个错误信息
            throw new AssertException(ResultCodeConstants.CODE_S0006, errors.get(errors.size() - 1));
        }
    }

    /**
     * 根据版本号判断版本是否升级
     *
     * @param sysVersion 系统配置版本号
     * @param appVersion app版本号
     * @return
     */
    public static boolean versionToUp(String sysVersion, String appVersion) {
        if (StringUtil.isBlank(sysVersion) || StringUtil.isBlank(appVersion)) {
            return true;
        }
        //版本相同不升级
        if (sysVersion.equals(appVersion)) {
            return false;
        }
        String[] sysSpilt = sysVersion.split("\\.");
        String[] appSplit = appVersion.split("\\.");
        for (int i = 0; i < sysSpilt.length; i++) {
            Integer sysInt = Integer.valueOf(sysSpilt[i]);
            //防止数组越界
            if (appSplit.length >= i + 1) {
                if (sysInt > Integer.valueOf(appSplit[i])) {
                    return true;
                }
            }
        }
        //处理如下情况  配置:1.0.0.11 app:1.0.0
        if (sysVersion.indexOf(appVersion) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 区转城市
     *
     * @param areCode
     * @return
     */
    public static String are2City(String areCode) {
        if (StringUtils.isEmpty(areCode) || areCode.length() < BusiConstants.REGION_CODE_LENGTH) {
            return areCode;
        }
        return areCode.substring(0, 4) + "00";
    }

    /**
     * 密码校验 8-16位字母加数字组合
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        //字符限制
        return password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
    }

    /**
     * http post 全量参数请求
     *
     * @param url 请求的url
     * @param paramMap 请求参数
     * @param headMap 请求头信息
     * @param timeout 超时时间（单位：毫秒）
     * @return
     */
    public static String httpPostRequestFull(String url, Map<String, Object> paramMap, Map<String, String> headMap, int timeout) {
        return HttpRequest.post(url)
                .addHeaders(headMap)
                .body(JSONObject.toJSONString(paramMap))
                .timeout(timeout)
                .execute()
                .body();
    }


}


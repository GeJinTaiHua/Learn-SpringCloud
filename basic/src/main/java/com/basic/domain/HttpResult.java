package com.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.basic.common.constants.ResultCodeConstants;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 返回对象
 */
public class HttpResult<T> extends BaseDomain {
    private static final long serialVersionUID = -1818484373630580958L;

    private static final Logger logger = LoggerFactory.getLogger(HttpResult.class);

    /**
     * 消息
     */
    @ApiModelProperty(value = "返回消息文本")
    private String msg;

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码")
    private String code;

    /**
     * 数据对象
     */
    @ApiModelProperty(value = "返回数据")
    private T data;
    /**
     * 请求状态
     */
    @ApiModelProperty(value = "请求状态")
    private int state;

    public HttpResult() {
        super();
    }

    public HttpResult(String msg, String code, T data) {
        super();
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    /**
     * 返回正确结果
     *
     * @param params
     * @return
     */
    public static <T> HttpResult<T> successResult(T params) {
        if (params == null) {
            params = (T) new Object();
        }
        HttpResult<T> result = new HttpResult<T>("", ResultCodeConstants.RESULT_CODE_SUCESS, params);
        logger.debug("调用成功:{}", result.toString());
        return result;
    }

    public static <T> HttpResult<T> successResult() {
        HttpResult<T> result = new HttpResult<T>(ResultCodeConstants.getMsg(ResultCodeConstants.RESULT_CODE_SUCESS), ResultCodeConstants.RESULT_CODE_SUCESS, (T) new Object());
        return result;
    }

    /**
     * 返回错误结果
     *
     * @param params
     * @return
     */
    public static <T> HttpResult<T> failResult(String... params) {
        String msg = "";
        String code = ResultCodeConstants.RESULT_CODE_FAIL;
        Integer length = params.length;
        if (length > 0) {
            msg = params[0];
        }
        if (length > 1) {
            code = params[1];
        }
        HttpResult<T> result = new HttpResult<T>(msg, code, (T) new Object());
        logger.info("调用失败:{}", JSONObject.toJSON(result));
        return result;
    }

    /**
     * 返回错误结果
     *
     * @param code 错误编码
     * @return
     */
    public static <T> HttpResult<T> failResultByCode(String code) {
        HttpResult<T> result = new HttpResult<>(ResultCodeConstants.getMsg(code), code, (T) new Object());
        logger.info("调用失败:{}", JSONObject.toJSON(result));
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setState(int state) {
        this.state = state;
    }
}

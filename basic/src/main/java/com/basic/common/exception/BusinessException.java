package com.basic.common.exception;

import com.basic.common.constants.ResultCodeConstants;
import lombok.Data;

/**
 * 业务断言错误返回类
 *
 * @date 2018/11/23 17:02
 **/
@Data
public class BusinessException extends RuntimeException{
    /**
     * 返回码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    /**
     *
     * @param code 返回码
     * @param msg 返回信息
     */
    public BusinessException(String code, String msg){
        this.code = code;
        this.msg = msg;
        this.data = "";
    }
    public BusinessException(String code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public BusinessException(String code){
        super(code);
        this.code = code;
        this.msg = ResultCodeConstants.getMsg(code);
        this.data = "";

    }


}

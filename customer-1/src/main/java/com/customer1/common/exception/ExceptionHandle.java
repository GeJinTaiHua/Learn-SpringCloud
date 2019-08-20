package com.customer1.common.exception;

import com.baidu.unbiz.fluentvalidator.exception.RuntimeValidateException;
import com.basic.common.exception.RepeatException;
import com.basic.domain.HttpResult;
import com.customer1.common.constants.ResultCodeConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局统一异常处理
 *
 * @ClassName ExceptionHandle
 * @Date 2018/11/15 18:21
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    public HttpResult handle(Exception e) {
        //业务异常
        if (e instanceof AssertException) {
            AssertException appException = (AssertException) e;
            return new HttpResult<>(appException.getMsg(), appException.getCode(), appException.getData());
        }
        //参数校验失败
        if (e instanceof MethodArgumentNotValidException || e instanceof RuntimeValidateException) {
            return HttpResult.failResultByCode(ResultCodeConstants.CODE_S0006);
        }
        //请求方式异常不进行错误日志打印
        if (!(e instanceof HttpRequestMethodNotSupportedException)) {
            logger.error("【系统异常】", e);
        } else {
            logger.error("【请求异常】-----------不支持的请求方式");
        }

        // 获取业务层错误信息并抛给前端
        try {
            UndeclaredThrowableException undeclaredThrowableException = (UndeclaredThrowableException) e;
            if (undeclaredThrowableException != null &&
                    undeclaredThrowableException.getUndeclaredThrowable() != null
                    ) {
                String message = undeclaredThrowableException.getUndeclaredThrowable().getMessage();
                if (StringUtils.isNotEmpty(message)) {
                    //抛给前端
                    return HttpResult.failResult(message);
                }
            }

        } catch (Exception ex) {
            //经测试，控制层自己的异常，在try代码中会报错，这里吃掉异常，不影响整个流程。
            // 还有拿得了业务层异常信息就拿，拿不了报错，吃掉异常往后走，不影响整个流程。
        }

        //参数校验失败

        return HttpResult.failResult(ResultCodeConstants.getMsg(ResultCodeConstants.CODE_S0003));
    }


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RepeatException.class)
    public String handlerException(RepeatException e) {
        logger.error("请勿重复提交请求！", e);
        return "请勿重复提交请求！";
    }
}

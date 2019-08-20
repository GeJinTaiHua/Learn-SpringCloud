package com.basic.common.exception;

import com.alibaba.fastjson.JSON;
import com.basic.common.constants.ResultCodeConstants;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Slf4j
public class FeignClientErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() != HttpStatus.OK.value()) {

            String errorContent;
            try {
                errorContent = Util.toString(response.body().asReader());
                log.info("response error mess:{}", errorContent);
                Exception rpcApiException = JSON.parseObject(errorContent, Exception.class);
                String msg = ResultCodeConstants.getMsg(rpcApiException.getMessage());
                if (StringUtils.isNotBlank(msg)) {
                    return new BusinessException(rpcApiException.getMessage(), msg);
                }
                return new BusinessException(ResultCodeConstants.CODE_S0002, rpcApiException.getMessage());

            } catch (Exception e) {
                log.error("FeignClientErrorDecoder Exception ", e);
                return new Exception("FeignClientErrorDecoder Exception", e);
            }

        }
        return new Exception("rpcApiException");

    }
}
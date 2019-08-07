package com.app.common.exception;

import com.alibaba.fastjson.JSON;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
@Configuration
public class FeignClientErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() != HttpStatus.OK.value()) {

            String errorContent;
            try {
                errorContent = Util.toString(response.body().asReader());
                log.info("FeignClientErrorDecoder-------:{}",errorContent);
                Exception rpcApiException = JSON.parseObject(errorContent, Exception.class);
                return rpcApiException;
            } catch (Exception e) {
                log.error("FeignClientErrorDecoder Exception ", e);
                return new Exception("FeignClientErrorDecoder Exception", e);
            }

        }
        return new Exception("rpcApiException");

    }
}
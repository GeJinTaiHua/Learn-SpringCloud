package com.app.common.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * 时间转换处理
 * @author
 * @date 2018/12/12 10:17
 **/
public class DateMessConverter extends AbstractHttpMessageConverter<Date> {
    /**
     * 定义字符编码，防止乱码
     */
    private static final Charset DEFAULT_CHARSET=Charset.forName("UTF-8");

    public DateMessConverter() {
        super(new MediaType("application", "json", Charset.forName("Utf-8")));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        //只对时间进行处理
        //return Date.class.isAssignableFrom(aClass);
        return true;
    }
    /**
     * 重写readlntenal 方法，处理请求的数据。
     */
    @Override
    protected Date readInternal(Class<? extends Date> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream body = httpInputMessage.getBody();
        String s = StreamUtils.copyToString(body, DEFAULT_CHARSET);
        if(StringUtils.isEmpty(s)){
            return null;
        }
        return new Date(Long.valueOf(s));
    }

    /**
     * 重写writeInternal ，处理如何输出数据到response。
     */
    @Override
    protected void writeInternal(Date date, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
       /* if(date == null){
            date = new Date(0);
        }
        StreamUtils.copy(date.getTime(),DEFAULT_CHARSET,httpOutputMessage.getBody());
        httpOutputMessage.getBody().write(date.get);*/
        System.out.println(123);
    }
}

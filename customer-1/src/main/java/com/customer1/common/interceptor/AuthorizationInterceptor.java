package com.customer1.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.basic.common.constants.BusiConstants;
import com.basic.domain.HttpResult;
import com.customer1.common.annotation.IgnoreToken;
import com.customer1.common.constants.ResultCodeConstants;
import com.customer1.common.context.UserContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    /**
     * 存放鉴权信息的Header名称，默认是Authorization
     */
    private String httpHeaderName = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 记录header
        Enumeration<String> er = request.getHeaderNames();
        while (er.hasMoreElements()) {
            String name = er.nextElement();
            if (log.isInfoEnabled()) {
                log.info("header name:[{}],header value:[{}]", name, request.getHeader(name));
            }
        }

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(IgnoreToken.class) != null || handlerMethod.getBeanType().getAnnotation(IgnoreToken.class) != null) {
            return true;
        } else {
            String token = request.getHeader(httpHeaderName);
            if (!StringUtils.isNotBlank(token)) {
                printMess(response, HttpResult.failResult("token不能为空！", ResultCodeConstants.RESULT_CODE_FAIL));
                return false;
            }

            //处理token
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }

    /**
     * @param response
     * @param httpResult
     * @Title: printMess
     * @Description: 客户端输出打印内容
     */
    private void printMess(HttpServletResponse response, HttpResult<?> httpResult) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(JSON.toJSONString(httpResult));
        } catch (Exception e) {
            log.error("response error", e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 接口加密的MD5验证方法
     * <p>
     * * @param text 明文
     * * @param time 时间搓
     * * @param sign 签名
     *
     * @return true/false
     * @throws Exception
     */
    private boolean verify(Map<String, String> map, HttpServletResponse response) throws Exception {
        //签名不能为空
        if (map.get("sign") == null) {
            printMess(response, HttpResult.failResultByCode(ResultCodeConstants.RESULT_CODE_FAIL));
            return false;
        }
        // byte[] text ,String time,String sign;
        //判断时间搓是否延时
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        Date timestamp = sdf.parse(map.get("time"));

        //盐（当前方法名）
        String salt = Thread.currentThread().getStackTrace()[1].getMethodName();
        //把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
        List<String> keys = new ArrayList<>(map.keySet());  //对map进行遍历
        //进行默认自然排序
        Collections.sort(keys);
        String prestr = "";
        StringBuilder stringBuilder = new StringBuilder(prestr);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = map.get(key);
            if (i == keys.size() - 1) {
                //将传递过来的参数进行拼接。拼接时，不包括最后一个&字符。
                //  prestr = prestr + key + "=" + value;
                stringBuilder = stringBuilder.append(key).append("=").append(value);

            } else {
                //  prestr = prestr + key + "=" + value + "&";
                stringBuilder = stringBuilder.append(key).append("=").append(value).append("&");
            }
        }
        //密钥（密钥=入参+时间搓+盐）32位小写
        String signText = DigestUtils.md5Hex(stringBuilder.toString() + timestamp + salt);
        //验证时间戳是否超过五分钟
        if ((dt.getTime() - timestamp.getTime()) / 60000 < 5) {
            //如果当前签名为传过来的签名则验签通过否则验签失败
            if (signText.equals(map.get("sign"))) {
                //md5验签通过
                printMess(response, HttpResult.successResult(BusiConstants.YAN_QIAN_TONG_GUO));
                return true;
            } else {
                //验签失败
                printMess(response, HttpResult.failResultByCode(BusiConstants.YAN_QIAN_SHI_BAI));
                return false;
            }
        }
        //间隔时长超过5分钟则验签失败
        printMess(response, HttpResult.failResultByCode(BusiConstants.YAN_QIAN_SHI_BAI));
        return false;
    }
}

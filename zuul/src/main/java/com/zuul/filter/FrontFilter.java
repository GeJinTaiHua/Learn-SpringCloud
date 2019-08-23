package com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 前置过滤器
 */
@Component
public class FrontFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(FrontFilter.class);

    @Autowired
    FilterUtils filterUtils;

    /**
     * 告诉zuul，该过滤器是前置过滤器、路由过滤器还是后置过滤器
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 不同类型过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 过滤器是否要执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的执行代码
     * 确保每个请求都有相关的关联ID，用于跟踪一系列服务调用的事件链；
     */
    @Override
    public Object run() {
        if (isCorrelationIdPresent()) {
            logger.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            logger.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
        return null;
    }

    /**
     * 检查自定义的header是否存在
     */
    private boolean isCorrelationIdPresent() {
        if (filterUtils.getCorrelationId() != null) {
            return true;
        }

        return false;
    }

    /**
     * 生成关联ID
     */
    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}

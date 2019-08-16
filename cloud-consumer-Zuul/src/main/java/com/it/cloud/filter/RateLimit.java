package com.it.cloud.filter;


import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流
 */
@Component
public class RateLimit extends ZuulFilter {

    //谷歌工具
    private static final RateLimiter RATE_LIMITER=RateLimiter.create(100);


    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //限流优先级放在最前
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        //如果没有token
        if (!RATE_LIMITER.tryAcquire()){

        }

        return null;
    }
}

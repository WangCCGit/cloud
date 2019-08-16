package com.it.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


//权限过滤  分买卖家
@Component
public class AuthFilter extends ZuulFilter {

    //过滤类型
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //过滤顺序   没法过滤类型下对应的相应的过滤顺序 顺序越小优先级越高
    @Override
    public int filterOrder() {
        return 0;
    }


    @Override
    public boolean shouldFilter() {
        return false;
    }


    //重写方法
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // String token = request.getHeader("token");
        /*
         * /order/create  只能买家访问
         * /order/finish  卖家访问
         * /product/list   都可访问
         * */

        return "ok";
    }



}

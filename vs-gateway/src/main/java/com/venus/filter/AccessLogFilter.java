package com.venus.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关请求最后一个过滤器
 * 用于打印请求日志：请求时间，请求uri
 */
@Component
public class AccessLogFilter extends ZuulFilter {

    /**
     * Intellij idea上git配置的设置
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    //该过滤器最后执行
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //请求上下文，可以传递到下一个过滤器
        //该次请求的上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Long startTime = (Long)ctx.get("startTime");
        String requestUri = request.getRequestURI();
        Long duration = System.currentTimeMillis()-startTime;
        System.out.println("请求URI：" + requestUri + ", 请求时间：" + duration);
        return null;
    }
}

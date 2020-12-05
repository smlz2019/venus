package com.venus.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 请求前过滤器
 */
@Component
public class PreRequestFilter extends ZuulFilter {

    //过滤器类型(预先被调用)
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //过滤器权重 越小优先执行
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否执行该过滤器 默认为false
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 得到当前的系统时间，将该时间放入到请求Zuul网关请求的上下文中
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());
        return null;
    }
}

package com.szf.microservicegatewayzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Allen_shen
 * @Date: 2019-05-05 16:01
 * @Description: java类作用描述
 */
public class PreRequestLogFilter extends ZuulFilter {

    private final static Logger LOGGER= LoggerFactory.getLogger(PreRequestLogFilter.class);


    //可选pre、route、post、error
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();
        LOGGER.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURL().toString()));

        return null;
    }
}

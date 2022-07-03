package com.vs.ad.advice;

import com.vs.ad.annotation.IgnoreResponseAdvice;
import com.vs.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //如果类上有注解：ignoreResponseAdvice则不过滤；
        if(returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        //如果方法上有注解：ignoreResponseAdvice则不过滤；
        if(returnType.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    /**
     * 对响应给前端的返回值封装
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        CommonResponse<Object> res = new CommonResponse<>(0, "");
        if(body==null) {
            return res;
        }else if(body instanceof CommonResponse) {
            res = (CommonResponse<Object>)body;
        }else {
            res.setData(body);
        }
        return res;
    }
}

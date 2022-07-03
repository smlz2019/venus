package com.vs.ad.advice;

import com.vs.ad.exception.AdException;
import com.vs.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    //@ExceptionHandler 默认对所有的异常进行处理;value有值的时候，对value对用的类进行处理；
    //当代码中抛出AdException时候，被handlerAdException捕获到，并且进行处理；
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest req, AdException ex) {
        CommonResponse<String> exRes = new CommonResponse<>(-1, "business error");
        exRes.setData(ex.getMessage());
        return exRes;
    }

}

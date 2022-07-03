package com.vs.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的相应
 */
//全参构造函数注解
@AllArgsConstructor
//无参构造函数注解
@NoArgsConstructor
@Data
public class CommonResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

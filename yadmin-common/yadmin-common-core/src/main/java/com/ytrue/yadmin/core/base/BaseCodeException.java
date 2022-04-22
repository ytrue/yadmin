package com.ytrue.yadmin.core.base;

import com.ytrue.yadmin.core.enums.ResponseCode;
import lombok.Getter;

/**
 * @author ytrue
 * @date 2022/4/20 17:40
 * @description 基础异常, 这个在全局异常要获得message和code的
 */
public abstract class BaseCodeException extends RuntimeException {

    /**
     * 状态码
     */
    @Getter
    private Integer code;

    public BaseCodeException() {
    }

    public BaseCodeException(String message) {
        super(message);
    }

    public BaseCodeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseCodeException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

}

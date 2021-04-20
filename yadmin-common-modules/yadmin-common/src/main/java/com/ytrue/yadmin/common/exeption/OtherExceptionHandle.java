package com.ytrue.yadmin.common.exeption;


import com.ytrue.yadmin.common.response.ResponseCode1;
import com.ytrue.yadmin.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yangyi
 * @date 2020/6/16 15:52
 * @description 捕获特定的异常
 */
@ControllerAdvice
@Slf4j
public class OtherExceptionHandle {

    /**
     * 捕获自定义验证的异常
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler({YadminException.class})
    public Object handleArithmeticException(
            YadminException exception,
            HttpServletResponse response
    ) {
        ResponseData.jsonOut(response, ResponseData.fail(
                ResponseCode1.BAD_REQUEST.getCode(), exception.getMessage()));
        return null;
    }
}

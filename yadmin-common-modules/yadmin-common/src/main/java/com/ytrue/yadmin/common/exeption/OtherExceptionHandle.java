package com.ytrue.yadmin.common.exeption;


import com.ytrue.yadmin.common.json.JsonUtil;
import com.ytrue.yadmin.common.response.ResponseCode;
import com.ytrue.yadmin.common.response.ResponseData;
import com.ytrue.yadmin.common.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yangyi
 * @date 2020/6/16 15:52
 * @description 捕获特定的异常
 */
@Slf4j
@ControllerAdvice
public class OtherExceptionHandle {


    /**
     * 捕获自定义验证的异常
     *
     * @param exception
     * @param response
     * @return
     */
    @ExceptionHandler({YadminException.class})
    public void handleArithmeticException(
            YadminException exception,
            HttpServletResponse response
    ) {
        ResponseUtil.renderJson(response, ResponseData.fail(
                ResponseCode.EXCEPTION.getCode(),
                exception.getMessage()
        ));
    }

    /**
     * 处理自动验证的异常
     *
     * @param exception
     * @param response
     */
    @ExceptionHandler({AutoValidException.class})
    public void handleAutoValidException(
            AutoValidException exception,
            HttpServletResponse response
    ) {
        ResponseUtil.renderJson(response, ResponseData.fail(
                ResponseCode.BAD_REQUEST.getCode(),
                ResponseCode.BAD_REQUEST.getMessage(),
                JsonUtil.toList(exception.getMessage())
        ));
    }

}

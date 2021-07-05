package com.ytrue.yadmin.exeption.handle;


import com.ytrue.yadmin.exeption.code.ExceptionCode;
import com.ytrue.yadmin.exeption.AutoValidException;
import com.ytrue.yadmin.exeption.YadminException;
import com.ytrue.yadmin.utils.GsonUtils;
import com.ytrue.yadmin.utils.R;
import com.ytrue.yadmin.utils.ResponseUtils;
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
        ResponseUtils.renderJson(response, R.fail(
                ExceptionCode.EXCEPTION.getCode(),
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
        ResponseUtils.renderJson(response, R.fail(
                ExceptionCode.BAD_REQUEST.getCode(),
                ExceptionCode.BAD_REQUEST.getMessage(),
                GsonUtils.fromList(exception.getMessage(), String.class)
        ));
    }

}

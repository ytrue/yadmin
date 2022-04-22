package com.ytrue.yadmin.core.excptions.handle;


import com.ytrue.yadmin.core.base.BaseCodeException;
import com.ytrue.yadmin.core.utils.ResultData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yangyi
 * @date 2022/6/15 16:27
 * @description 全局异常处理类
 */
@Controller
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandle implements ErrorController {

    private final ErrorAttributes errorAttributes;

    /**
     * 全局异常处理
     *
     * @param response
     * @param req
     * @return
     */
    @RequestMapping("error")
    @ResponseBody
    public ResultData<Object> error(HttpServletResponse response, WebRequest req) {
        log.info("GlobalExceptionHandle：被触发了");
        //设置200，方便前端处理
        response.setStatus(200);
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(req,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS
                ));

        //返回错误
        String errorMessage = (String) errorAttributes.get("message");
        Integer errorCode = (Integer) errorAttributes.get("status");

        //针对BaseCodeException处理
        Throwable error = this.errorAttributes.getError(req);
        if (error instanceof BaseCodeException) {
            errorMessage = error.getMessage();
            errorCode = ((BaseCodeException) error).getCode();

            log.info(errorCode.toString());
        }

        return ResultData.fail(errorCode, errorMessage);
    }
}
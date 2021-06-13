package com.ytrue.yadmin.common.exeption.handle;


import com.ytrue.yadmin.common.enums.ResponseCode;
import com.ytrue.yadmin.common.utils.ResponseData;
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
 * @date 2020/6/15 16:27
 * @description 全局异常处理类, 这个类会在以后替换掉，目前暂时不做升级
 */
@Controller
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandle implements ErrorController {

    private final ErrorAttributes errorAttributes;

    /**
     * 这个会在2.5的版本删除掉，不会有影响
     *
     * @return
     */
    @Override
    public String getErrorPath() {
        return null;
    }


    /**
     * 全局异常处理
     *
     * @param response
     * @param req
     * @return
     */
    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletResponse response, WebRequest req) {
        log.info("GlobalExceptionHandle：被触发了");
        //设置200，方便前端处理
        response.setStatus(ResponseCode.SUCCESS.getCode());
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(req,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS
                ));
        //返回错误
        return ResponseData.
                fail((Integer) errorAttributes.get("status"),
                        errorAttributes.get("error") + "：" + errorAttributes.get("message"));
    }
}

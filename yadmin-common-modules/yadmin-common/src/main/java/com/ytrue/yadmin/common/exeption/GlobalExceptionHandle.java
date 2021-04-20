package com.ytrue.yadmin.common.exeption;


import com.ytrue.yadmin.common.response.ResponseCode1;
import com.ytrue.yadmin.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @description 全局异常处理类,这个类会在以后替换掉，目前暂时不做升级
 */
@Controller
@Slf4j
public class GlobalExceptionHandle implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletResponse response, WebRequest req) {
        log.info("GlobalExceptionHandle：被触发了");
        //设置200，方便前端处理
        response.setStatus(ResponseCode1.SUCCESS.getCode());
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(req, false);
        //返回错误
        return ResponseData.
                fail((Integer) errorAttributes.get("status"),
                        errorAttributes.get("error") + "：" + errorAttributes.get("message"));
    }
}

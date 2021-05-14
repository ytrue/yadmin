package com.ytrue.yadmin.common.annotation.handle;


import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ytrue
 * @date 2021/4/19 09:25
 * @ControllerAdvice 设置拦截目录，basePackages 填写到你controller对应的包
 * ResponseBodyAdvice 统一响应处理
 */

@Slf4j
@ControllerAdvice(basePackages = {"com.ytrue.yadmin",})
public class WrapRespAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean wrap = methodParameter.hasMethodAnnotation(WrapResp.class);
        if (!wrap) {
            WrapResp ann = methodParameter.getMethod() == null ? null : methodParameter.getMethod().getDeclaringClass().getAnnotation(WrapResp.class);
            if (null != ann) {
                wrap = true;
            }
        }
        return wrap;
    }

    @Override
    public Object beforeBodyWrite(
            Object resp,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse
    ) {
        //字符串的返回有问题，所以暂时直接返回
        if (resp instanceof ResponseData || resp instanceof String) {
            return resp;
        } else {
            return ResponseData.success(resp);
        }

    }
}

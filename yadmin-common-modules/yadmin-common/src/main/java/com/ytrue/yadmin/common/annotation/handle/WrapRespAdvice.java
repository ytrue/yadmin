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
@ControllerAdvice(basePackages = {
        "com.ytrue.yadmin.modules.sys.rest",
        "com.ytrue.yadmin.modules.security.rest",
})
public class WrapRespAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        boolean wrap = methodParameter.hasMethodAnnotation(WrapResp.class);
        if (!wrap) {
            WrapResp ann = methodParameter.getMethod() == null ? null : methodParameter.getMethod().getDeclaringClass().getAnnotation(WrapResp.class);
            if (ann != null) {
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
        //这里要处理一下，如果返回的数据是ResponseData,就是直接返回
        //这个怎么搞呢, 我又想返回null,又想抓到以前的数据

        System.out.println(resp);
        if (resp instanceof ResponseData) {
            log.info("1111111111");
            return resp;
        } else {
            log.info("222222222222");
            return ResponseData.success(resp);
        }

    }
}

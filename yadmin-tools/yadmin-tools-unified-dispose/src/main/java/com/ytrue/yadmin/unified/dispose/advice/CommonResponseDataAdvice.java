package com.ytrue.yadmin.unified.dispose.advice;


import com.ytrue.yadmin.unified.dispose.annotation.IgnoreResponseAdvice;
import com.ytrue.yadmin.unified.dispose.properties.UnifiedDisposeResponseDataProperties;
import com.ytrue.yadmin.utils.GsonUtils;
import com.ytrue.yadmin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author ytrue
 * @date 2021/7/8 10:58
 * @description {@link IgnoreResponseAdvice} 处理解析 {@link ResponseBodyAdvice} 统一返回包装器
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {


    private final static String STRING = "java.lang.String";

    /**
     * 注入属性
     */
    @Autowired
    private UnifiedDisposeResponseDataProperties unifiedDisposeResponseDataProperties;


    @Autowired
    private HttpServletRequest request;

    /**
     * 判断是否要执行beforeBodyWrite方法，true为执行，false不执行
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return filter(methodParameter);
    }

    /**
     * 对response处理的执行方法
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Nullable
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 返回值为 Object 类型  并且返回为空是  AbstractMessageConverterMethodProcessor#writeWithMessageConverters 方法
        // 无法触发调用本类的 beforeBodyWrite 处理，开发在 Controller 尽量避免直接使用 Object 类型返回。

        // o is null -> return response
        if (o == null) {
            // 当 o 返回类型为 string 并且为null会出现 java.lang.ClassCastException: Result cannot be cast to java.lang.String
            if (STRING.equals(methodParameter.getParameterType().getName())) {
                return GsonUtils.to(R.success()).toString();
            }
            return R.success();
        }
        // o is instanceof ConmmonResponse -> return o
        if (o instanceof R) {
            return o;
        }
        // string 特殊处理 java.lang.ClassCastException: Result cannot be cast to java.lang.String
        if (o instanceof String) {
            return GsonUtils.to(R.success(o)).toString();
        }
        return R.success(o);
    }


    /**
     * 过滤
     *
     * @param methodParameter
     * @return
     */
    private Boolean filter(MethodParameter methodParameter) {

        //是否开启
        Boolean enabled = unifiedDisposeResponseDataProperties.getEnabled();
        if (!enabled) {
            return false;
        }

        //过滤url
        List<String> adviceFilterUrl = unifiedDisposeResponseDataProperties.getAdviceFilterUrl();
        PathMatcher matcher = new AntPathMatcher();
        String uri = request.getRequestURI();
        for (String s : adviceFilterUrl) {
            if (matcher.match(s, uri)) {
                return false;
            }
        }


        Class<?> declaringClass = methodParameter.getDeclaringClass();
        // 检查过滤包路径
        long count = unifiedDisposeResponseDataProperties.getAdviceFilterPackage().stream()
                .filter(l -> declaringClass.getName().contains(l)).count();
        if (count > 0) {
            return false;
        }
        // 检查<类>过滤列表
        if (unifiedDisposeResponseDataProperties.getAdviceFilterClass().contains(declaringClass.getName())) {
            return false;
        }
        // 检查注解是否存在
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return !Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseAdvice.class);
    }


}

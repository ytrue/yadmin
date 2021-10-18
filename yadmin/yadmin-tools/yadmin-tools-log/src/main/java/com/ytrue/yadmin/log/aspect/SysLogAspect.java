package com.ytrue.yadmin.log.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.ytrue.yadmin.exeption.code.ExceptionCode;
import com.ytrue.yadmin.log.entity.OptLogDTO;
import com.ytrue.yadmin.log.event.SysLogEvent;
import com.ytrue.yadmin.log.util.LogUtil;
import com.ytrue.yadmin.util.GsonUtil;
import com.ytrue.yadmin.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author ytrue
 * @date 2021/7/1 13:39
 * @description 操作日志使用spring event异步入库
 */
@Slf4j
@Aspect
public class SysLogAspect {

    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();

    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.ytrue.yadmin.log.annotation.SysLog)")
    public void sysLogAspect() {

    }

    private OptLogDTO get() {
        OptLogDTO sysLog = THREAD_LOCAL.get();
        if (sysLog == null) {
            return new OptLogDTO();
        }
        return sysLog;
    }


    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) {
        tryCatch((val) -> {
            // 开始时间
            OptLogDTO sysLog = get();
            sysLog.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            String controllerDescription = "";

            Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);


            if (api != null) {
                String[] tags = api.tags();
                if (tags.length > 0) {
                    controllerDescription = tags[0];
                }
            }

            String controllerMethodDescription = LogUtil.getControllerMethodDescription(joinPoint);
            //判断这个sysLog注解的value是否为空,等于空就去获取ApiOperation注解的value
            if (StrUtil.isEmpty(controllerMethodDescription)) {
                //获得切面方法上的指定注解
                Class<?> clazz = joinPoint.getTarget().getClass();
                String methodName = joinPoint.getSignature().getName();
                Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
                Method method;
                try {
                    method = clazz.getMethod(methodName, parameterTypes);
                    controllerMethodDescription = method.getAnnotation(ApiOperation.class).value();
                } catch (NoSuchMethodException e) {
                    controllerMethodDescription = "";
                }
            }


            if (StrUtil.isEmpty(controllerDescription)) {
                sysLog.setDescription(controllerMethodDescription);
            } else {
                sysLog.setDescription(controllerDescription + "-" + controllerMethodDescription);
            }

            // 类名
            sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
            //获取执行的方法名
            sysLog.setActionMethod(joinPoint.getSignature().getName());

            // 参数
            Object[] args = joinPoint.getArgs();

            String strArgs = "";
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            try {
                if (!request.getContentType().contains("multipart/form-data")) {
                    strArgs = GsonUtil.to(args);
                }
            } catch (Exception e) {
                try {
                    strArgs = Arrays.toString(args);
                } catch (Exception ex) {
                    log.warn("解析参数异常", ex);
                }
            }
            sysLog.setParams(getText(strArgs));
            sysLog.setRequestIp(ServletUtil.getClientIP(request));
            sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
            sysLog.setHttpMethod(request.getMethod());
            sysLog.setUa(StrUtil.sub(request.getHeader("user-agent"), 0, 500));
            sysLog.setStartTime(LocalDateTime.now());
            THREAD_LOCAL.set(sysLog);
        });
    }


    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            log.warn("记录操作日志异常", e);
            THREAD_LOCAL.remove();
        }
    }

    /**
     * 返回通知,目前后置处理器我想去掉它，不使用失败响应，全部抛异常是可以的
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        tryCatch((aaa) -> {
            R r = Convert.convert(R.class, ret);
            OptLogDTO sysLog = get();
            if (r == null) {
                sysLog.setType("OPT");
            } else {
                if (r.getCode() == null) {
                    sysLog.setType("OPT");
                } else {
                    if (r.getCode().equals(ExceptionCode.SUCCESS.getCode())) {
                        sysLog.setType("OPT");
                    } else {
                        sysLog.setType("EX");
                        sysLog.setExDetail(r.getMessage());
                        //sysLog.setResult(getText(r.toString()));
                    }
                }
            }
            publishEvent(sysLog);
        });

    }

    private void publishEvent(OptLogDTO sysLog) {
        sysLog.setFinishTime(LocalDateTime.now());
        sysLog.setConsumingTime(sysLog.getStartTime().until(sysLog.getFinishTime(), ChronoUnit.MILLIS));
        applicationContext.publishEvent(new SysLogEvent(sysLog));
        THREAD_LOCAL.remove();
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        tryCatch((aaa) -> {
            OptLogDTO sysLog = get();
            sysLog.setType("EX");
            // 异常对象
            sysLog.setExDetail(LogUtil.getStackTrace(e));
            // 异常信息
            sysLog.setExDesc(e.getMessage());
            publishEvent(sysLog);
        });
    }

    /**
     * 截取指定长度的字符串
     *
     * @param val
     * @return
     */
    private String getText(String val) {
        return StrUtil.sub(val, 0, 65535);
    }

}

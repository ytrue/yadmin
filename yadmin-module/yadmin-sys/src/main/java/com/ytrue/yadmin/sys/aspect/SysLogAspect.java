package com.ytrue.yadmin.sys.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import com.ytrue.yadmin.common.json.JsonUtil;
import com.ytrue.yadmin.common.util.IpHelper;
import com.ytrue.yadmin.sys.model.SysLog;
import com.ytrue.yadmin.sys.service.SysLogService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 日志注解切面
 */
@Aspect
@Component
@AllArgsConstructor
public class SysLogAspect {

    private final SysLogService sysLogService;

    /**
     * 切面具体操作
     *
     * @param joinPoint
     * @param sysLog
     * @return
     * @throws Throwable
     */
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint joinPoint, com.ytrue.yadmin.sys.annotation.SysLog sysLog) throws Throwable {

        long beginTime = SystemClock.now();
        //执行方法
        Object result = joinPoint.proceed();
        //执行时长(毫秒)
        long time = SystemClock.now() - beginTime;


        SysLog sysLogEntity = new SysLog();
        if (sysLog != null) {
            //注解上的描述
            sysLogEntity.setOperation(sysLog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        sysLogEntity.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();

        String params = JsonUtil.toJsonString(args[0]);
        sysLogEntity.setParams(params);

        //设置IP地址
        sysLogEntity.setIp(IpHelper.getIpAddr());

        //用户名
        String username = "临时用户名";
        sysLogEntity.setUsername(username);

        sysLogEntity.setTime(time);
        sysLogEntity.setCreateDate(DateUtil.date());
        //保存系统日志
        sysLogService.save(sysLogEntity);

        return result;
    }

}

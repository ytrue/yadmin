package com.ytrue.yadmin.sys.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import com.ytrue.yadmin.common.utils.GsonUtils;
import com.ytrue.yadmin.common.utils.IpHelper;
import com.ytrue.yadmin.common.utils.JwtUtils;
import com.ytrue.yadmin.sys.dao.SysLogDao;
import com.ytrue.yadmin.sys.model.SysLog;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 日志注解切面
 */
@Aspect
@Component
@AllArgsConstructor
public class SysLogAspect {


    private final SysLogDao sysLogDao;

    //private final HttpServletRequest request;

    //private final JwtUtils jwtUtils;


    /**
     * 切面具体操作
     *
     * @param joinPoint
     * @param sysLog
     * @return
     * @throws Throwable
     */
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint joinPoint, com.ytrue.yadmin.common.annotation.SysLog sysLog) throws Throwable {

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

        // String params = JsonUtil.toJsonString(args[0]);
        String params = GsonUtils.to(args[0]);

        sysLogEntity.setParams(params);

        //设置IP地址
        sysLogEntity.setIp(IpHelper.getIpAddr());

        //这里使用SecurityContextHolder的上下文来获取用户名
        sysLogEntity.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        sysLogEntity.setTime(time);
        sysLogEntity.setCreateDate(LocalDateTime.now());
        //保存系统日志
        sysLogDao.insert(sysLogEntity);
        return result;
    }


}

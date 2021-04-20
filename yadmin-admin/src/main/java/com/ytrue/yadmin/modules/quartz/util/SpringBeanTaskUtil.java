package com.ytrue.yadmin.modules.quartz.util;

import cn.hutool.core.util.StrUtil;
import com.ytrue.yadmin.common.util.SpringContextUtils;
import com.ytrue.yadmin.modules.quartz.model.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author ytrue
 * @date 2021/4/8 15:36
 * @description 定时任务spring bean 执行定时任务
 */
@Slf4j
public class SpringBeanTaskUtil {

    public static void invokeMethod(ScheduleJob scheduleJob) {
        Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());
        try {
            if (StrUtil.isNotEmpty(scheduleJob.getParams())) {
                Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethodName(), String.class);
                ReflectionUtils.makeAccessible(method);
                method.invoke(target, scheduleJob.getParams());
            } else {
                Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethodName());
                ReflectionUtils.makeAccessible(method);
                method.invoke(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("执行定时任务失败", e);
        }
    }
}

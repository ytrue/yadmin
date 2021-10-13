package com.ytrue.yadmin.chat.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ytrue.yadmin.log.entity.OptLogDTO;
import com.ytrue.yadmin.log.event.SysLogListener;
import com.ytrue.yadmin.modules.system.dao.SysLogDao;
import com.ytrue.yadmin.modules.system.dao.SysUserDao;
import com.ytrue.yadmin.modules.system.model.SysLog;
import com.ytrue.yadmin.modules.system.model.SysUser;
import com.ytrue.yadmin.modules.system.service.mapstruct.LogManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 * @date 2021/7/1 17:45
 * @description 日志配置类
 */

@Slf4j
@Configuration
public class LogAutoConfiguration {
    /**
     * 自动配置日志监听器组件
     *
     * @param logService
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SysLogListener sysLogListener(LogService logService) {
        return new SysLogListener(logService::saveLog);
    }


    @Slf4j
    @Service
    @AllArgsConstructor
    public static class LogService {

        private final LogManager logManager;

        private final SysLogDao sysLogDao;

        private final SysUserDao sysUserDao;

        /**
         * 保存日志
         *
         * @param optLogDTO
         */
        public void saveLog(OptLogDTO optLogDTO) {
            SysLog sysLog = logManager.toEntity(optLogDTO);
            //获取id
            if (!StrUtil.hasEmpty(sysLog.getUsername())) {
                SysUser sysUser = sysUserDao.selectOne(
                        new QueryWrapper<SysUser>().
                                lambda().
                                eq(SysUser::getUsername, sysLog.getUsername())
                );
                sysLog.setUserId(sysUser.getUserId());
            }
            sysLogDao.insert(sysLog);
        }
    }

}

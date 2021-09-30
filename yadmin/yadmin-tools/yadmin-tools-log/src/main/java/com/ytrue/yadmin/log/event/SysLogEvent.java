package com.ytrue.yadmin.log.event;


import com.ytrue.yadmin.log.entity.OptLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @author ytrue
 * @date 2021/7/1 13:39
 * @description 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1614899216507220792L;

    public SysLogEvent(OptLogDTO source) {
        super(source);
    }
}

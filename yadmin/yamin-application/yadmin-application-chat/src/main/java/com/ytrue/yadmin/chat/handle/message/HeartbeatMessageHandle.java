package com.ytrue.yadmin.chat.handle.message;

import com.ytrue.yadmin.chat.constant.SendType;
import com.ytrue.yadmin.chat.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2021/10/13 14:20
 * @description 心跳处理器
 */
@Slf4j
@Component
public class HeartbeatMessageHandle extends AbstractMessageHandle {

    @Override
    public void handle(Message message) {
        super.handle(message);
    }

    @Override
    public void afterPropertiesSet() {
        MessageHandleFactory.register(SendType.HEARTBEAT, this);
    }
}

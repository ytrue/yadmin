package com.ytrue.yadmin.chat.listener;

import com.ytrue.yadmin.chat.dto.Message;
import com.ytrue.yadmin.chat.handle.message.MessageHandleFactory;
import com.ytrue.yadmin.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2021/4/7 11:22
 * redis消息监听处理
 */
@Component
@Slf4j
public class RedisListenerHandle {

    /**
     * 消息监听，这个后面可以替换成消息中间件，原理一样的
     *
     * @param message
     */
    public void receiveMessage(String message) {
        Message msg = GsonUtils.from(message, Message.class);
        MessageHandleFactory.getInvokeStrategy(msg.getSendType()).handle(msg);
    }

}

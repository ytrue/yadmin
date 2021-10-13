package com.ytrue.yadmin.chat.listener;

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
        log.info("我接收到了redis订阅的消息"+message);
    }

}

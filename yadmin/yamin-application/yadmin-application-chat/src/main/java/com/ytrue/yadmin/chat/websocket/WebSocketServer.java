package com.ytrue.yadmin.chat.websocket;

import cn.hutool.core.convert.Convert;
import com.ytrue.yadmin.chat.constant.RedisKey;
import com.ytrue.yadmin.chat.handle.session.CloseSessionHandle;
import com.ytrue.yadmin.chat.handle.session.RegisterSessionHandle;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * @author ytrue
 * @date 2021/10/11 15:08
 * @description WebSocket服务类
 */
@Slf4j
@Component
@AllArgsConstructor
public class WebSocketServer extends AbstractWebSocketHandler {


    private final StringRedisTemplate redisTemplate;

    private final RegisterSessionHandle registerSessionHandle;

    private final CloseSessionHandle closeSessionHandle;

    /**
     * socket 建立成功事件
     *
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        //注册
        registerSessionHandle.handle(session, Convert.toLong(session.getAttributes().get("userId")));
    }

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     */
    @Override
    @SneakyThrows
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        redisTemplate.convertAndSend(RedisKey.CHAT_SESSION_SUBJECT, message.getPayload());
    }

    /**
     * socket 断开连接时
     *
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        //关闭
        closeSessionHandle.handle(session);
    }


    /**
     * 异常
     *
     * @param session
     * @param exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        //关闭
        log.error("websocket连接异常", exception);
        closeSessionHandle.handle(session);
    }
}

package com.ytrue.yadmin.chat.websocket;

import com.ytrue.yadmin.chat.constant.RedisKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.time.LocalDateTime;

/**
 * @author ytrue
 * @date 2021/10/11 15:08
 * @description WebSocket服务类
 */
@Slf4j
@Component
@AllArgsConstructor
public class WebSocketServer extends AbstractWebSocketHandler {


    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * socket 建立成功事件
     *
     * @param session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("连接建立完成");
    }

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        // 读取客户端消息
        Object token = session.getAttributes().get("user");
        String payload = message.getPayload();
        String msg = "服务端已接收到用户 [" + token + "] 的消息,消息内容为:" +
                payload + ",当前服务器时间: " +
                LocalDateTime.now();


        //发送消息
        redisTemplate.convertAndSend(RedisKey.CHAT_SESSION_SUBJECT.name(), "123.......");
        session.sendMessage(new TextMessage(msg));


    }

    /**
     * socket 断开连接时
     *
     * @param session
     * @param status
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("断开连接");
    }


    /**
     * 异常
     *
     * @param session
     * @param exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("连接异常");
    }
}

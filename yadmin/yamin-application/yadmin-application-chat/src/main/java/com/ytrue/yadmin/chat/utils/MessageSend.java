package com.ytrue.yadmin.chat.utils;


import lombok.SneakyThrows;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author ytrue
 * @date 2021/10/11 17:42
 * @description 发送消息
 */
public class MessageSend {


    /**
     * 指定session发送消息
     */
    @SneakyThrows
    public static void appoint(WebSocketSession session, String message) {
        //判断session是否是关闭的，关闭的就不发送
        if (session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }


    /**
     * 广播消息
     *
     * @param message
     */
    public static void broadcast(String message) {

    }
}

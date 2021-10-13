package com.ytrue.yadmin.chat.utils;


import lombok.SneakyThrows;

import javax.websocket.Session;
import java.util.List;

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
    public static void appoint(Session session, String message) {
        session.getBasicRemote().sendText(message);
    }

    /**
     * 指定id发送消息
     *
     * @param userId
     * @param message
     */
    public static void appoint(Long userId, String message) {

    }

    /**
     * 批量id 发送消息
     *
     * @param userIds
     * @param message
     */
    public static void appoint(List<Long> userIds, String message) {

    }


    /**
     * 广播消息
     *
     * @param message
     */
    public static void broadcast(String message) {

    }
}

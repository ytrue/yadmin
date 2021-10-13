package com.ytrue.yadmin.chat.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ytrue
 * @date 2021/10/11 16:24
 * @description WebSocket Session管理
 */
@Slf4j
public class WebSocketSessionManager {

    /**
     * websocket 会话池
     */
    private static ConcurrentHashMap<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    /**
     * 添加 websocket 会话
     *
     * @param key
     * @param session
     */
    public static void add(String key, WebSocketSession session) {
        webSocketSessionMap.put(key, session);
    }

    /**
     * 移除 websocket 会话,并将该会话内容返回
     *
     * @param key
     * @return
     */
    public static WebSocketSession remove(String key) {
        return webSocketSessionMap.remove(key);
    }

    /**
     * 删除 websocket,并关闭连接
     *
     * @param key
     */
    public static void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                log.error("Websocket session close exception ", e);
            }
        }
    }

    /**
     * 获取 websocket 会话
     *
     * @param key
     * @return
     */
    public static WebSocketSession get(String key) {
        return webSocketSessionMap.get(key);
    }

    /**
     * 获得所有的会话
     *
     * @return
     */
    public static ConcurrentHashMap<String, WebSocketSession> all() {
        return webSocketSessionMap;
    }

    /**
     * 获取会话数量
     *
     * @return
     */
    public static int count() {
        return webSocketSessionMap.size();
    }
}

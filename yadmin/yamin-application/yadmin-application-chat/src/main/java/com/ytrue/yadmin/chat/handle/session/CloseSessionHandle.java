package com.ytrue.yadmin.chat.handle.session;

import com.ytrue.yadmin.chat.constant.RedisKey;
import com.ytrue.yadmin.chat.util.WebSocketSessionManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ytrue
 * @date 2021/10/13 14:17
 * @description 关闭连接的处理器
 */
@Slf4j
@Component
@AllArgsConstructor
public class CloseSessionHandle {
    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 处理关闭
     *
     * @param session 会话
     */
    public void handle(WebSocketSession session) {
        //获得所有的连接
        ConcurrentHashMap<Long, WebSocketSession> onlineSession = WebSocketSessionManager.getOnlineSession();
        List<Long> list = getKeyList(onlineSession, session);
        //删除所有的
        list.forEach(userId -> {
            WebSocketSessionManager.removeSession(userId);
            //删除redis的
            redisTemplate.opsForSet().remove(RedisKey.CHAT_ONLINE_USER_ID, userId);
            log.info("userId：" + userId + "连接关闭");
        });
    }


    /**
     * 根据value值获取到对应的所有的key值
     *
     * @param map   map
     * @param value 值
     * @return
     */
    private List<Long> getKeyList(ConcurrentHashMap<Long, WebSocketSession> map, WebSocketSession value) {
        List<Long> keyList = new ArrayList<>();
        for (Long getKey : map.keySet()) {
            if (map.get(getKey).equals(value)) {
                keyList.add(getKey);
            }
        }
        return keyList;
    }
}

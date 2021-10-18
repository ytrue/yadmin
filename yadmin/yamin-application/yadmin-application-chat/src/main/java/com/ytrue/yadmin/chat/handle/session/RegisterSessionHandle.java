package com.ytrue.yadmin.chat.handle.session;

import com.ytrue.yadmin.chat.constant.RedisKey;
import com.ytrue.yadmin.chat.util.WebSocketSessionManager;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author ytrue
 * @date 2021/10/13 14:18
 * @description 连接建立完毕处理器
 * <p>
 * 一旦使用关于Spring的注解出现在类里，
 * 例如我在实现类中用到了@Autowired注解，
 * 被注解的这个类是从Spring容器中取出来的，
 * 那调用的实现类也需要被Spring容器管理，
 * 加上@Component
 */
@Slf4j
@Component
@AllArgsConstructor
public class RegisterSessionHandle {

    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 处理注册
     *
     * @param session 会话
     * @param userId 用户id
     */
    @SneakyThrows
    public void handle(WebSocketSession session, Long userId) {
        //判断是否注册了,这个可能返回null，要加一个null判断
        Boolean b = redisTemplate.opsForSet().isMember(RedisKey.CHAT_ONLINE_USER_ID, userId);
        if (b != null) {
            if (b){
                //这里要声明一下,这个要返回给客户端，告诉它已经登录了
                session.sendMessage(new TextMessage("你已经登录了"));
                session.close();
                return;
            }
        }
        //这里还要对redis操作，把userId存到set里面去
        WebSocketSessionManager.createOnlineSession(userId, session);
        //存到redis里面去，失败的话这里要断开来连接
        redisTemplate.opsForSet().add(RedisKey.CHAT_ONLINE_USER_ID, userId);
        log.info("userId: " + userId + "注册成功");
    }

}

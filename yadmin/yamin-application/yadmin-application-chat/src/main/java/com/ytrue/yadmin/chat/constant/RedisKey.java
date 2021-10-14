package com.ytrue.yadmin.chat.constant;

/**
 * @author ytrue
 * @date 2021/10/12 16:12
 * @description RedisKey
 */
public interface RedisKey {

    /**
     * 共享session订阅的主题
     */
    String CHAT_SESSION_SUBJECT = "chat_session_subject";

    /**
     * set类型，存放在线的用户id
     */
    String CHAT_ONLINE_USER_ID = "chat_online_user_id";
}

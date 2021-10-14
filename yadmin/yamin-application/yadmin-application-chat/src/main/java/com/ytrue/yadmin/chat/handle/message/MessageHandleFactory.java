package com.ytrue.yadmin.chat.handle.message;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author ytrue
 * @date 2021/10/14 15:25
 * @description 消息处理工厂
 */
public class MessageHandleFactory {

    private static final Map<String, AbstractMessageHandle> MAP = new HashMap<>();

    /**
     * 获得实例
     *
     * @param str
     * @return
     */
    public static AbstractMessageHandle getInvokeStrategy(String str) {
        return Optional.ofNullable(MAP.get(str)).orElseThrow(RuntimeException::new);
    }

    /**
     * 注册
     *
     * @param str
     * @param handler
     */
    public static void register(String str, AbstractMessageHandle handler) {
        if (StrUtil.isEmpty(str) || null == handler) {
            return;
        }
        MAP.put(str, handler);
    }
}

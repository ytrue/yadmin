package com.ytrue.yadmin.chat.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

/**
 * @author ytrue
 * @date 2021/10/12 11:02
 * @description 握手拦截器
 */
@Slf4j
@Component
public class WebSocketHandShakeInterceptor implements HandshakeInterceptor {


    private final static String UTF8 = "utf-8";

    /**
     * websocket
     * 握手之前，这里校验权限，和判断用户是否存和是否存在好友关系，没有就断开，再去判断是否已经登录，已经登录的话，就把之前的踢下线
     * 获取url参数，存入map里
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     */
    @Override
    public boolean beforeHandshake(
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse,
            WebSocketHandler webSocketHandler,
            Map<String, Object> map
    ) {
        log.info("123xxxxxxxxxxxxxxxxxxx");
        // 获取请求参数
        Map<String, List<String>> urlParameters = HttpUtil.decodeParams(serverHttpRequest.getURI().getQuery(), UTF8);
        List<String> tokens = urlParameters.get("token");
        if (tokens != null) {
            if (StrUtil.isNotBlank(tokens.get(0))) {
                map.put("userId", 1);
                log.info("验证成功");
                return true;
            }
        }
        log.info("验证不通过");
        return false;
    }

    /**
     * websocket 握手之后
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse,
            WebSocketHandler webSocketHandler,
            Exception e
    ) {

    }
}


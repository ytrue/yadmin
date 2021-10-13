package com.ytrue.yadmin.chat.config;

import com.ytrue.yadmin.chat.interceptor.WebSocketHandShakeInterceptor;
import com.ytrue.yadmin.chat.websocket.WebSocketServer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author ytrue
 * @date 2021/10/11 15:59
 * @description WebSocketC配置, @EnableWebSocket 开启WebSocket
 */
@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfigurer implements
        org.springframework.web.socket.config.annotation.WebSocketConfigurer {


    /**
     * 握手拦截器
     */
    private final WebSocketHandShakeInterceptor webSocketHandShakeInterceptor;


    /**
     * 消息处理器
     */
    private final WebSocketServer webSocketServer;


    /**
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint,这个暂无不用，先保留
     *
     * @return ServerEndpointExporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 如果不是注解形式使用WebSocket,需要在该处进行声明,否则为空即可
     *
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(webSocketServer, "/chat")
                .addInterceptors(webSocketHandShakeInterceptor)
                .setAllowedOrigins("*");
    }
}

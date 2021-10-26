package com.ytrue.yadmin.chat.handle.message;

import com.ytrue.yadmin.chat.constant.MessageStatus;
import com.ytrue.yadmin.chat.constant.SendType;
import com.ytrue.yadmin.dto.MessageDTO;
import com.ytrue.yadmin.chat.util.MessageSend;
import com.ytrue.yadmin.chat.util.WebSocketSessionManager;
import com.ytrue.yadmin.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author ytrue
 * @date 2021/10/13 14:21
 * @description 一对一聊天处理器
 */
@Slf4j
@Component
public class SingleMessageHandle extends AbstractMessageHandle {

    @Override
    public void handle(MessageDTO messageDTO) {
        //把消息改成 成功状态
        messageDTO.setStatus(MessageStatus.SUCCEED);
        //获取接收者的session
        WebSocketSession toContactSession = WebSocketSessionManager.getSessionByUserId(messageDTO.getToContactId());
        //获得发送者的session
        WebSocketSession fromContactSession = WebSocketSessionManager.getSessionByUserId(messageDTO.getFromUser().getId());
        //异步的插入数据库
        //xxxxxxxxxxxxxxxxxx

        //向接收者发送消息
        MessageSend.appoint(toContactSession, GsonUtil.to(messageDTO));
        //向发送者发送消息，告诉它消息发送成功了
        MessageSend.appoint(fromContactSession, GsonUtil.to(messageDTO));
    }

    @Override
    public void afterPropertiesSet() {
        MessageHandleFactory.register(SendType.SINGLE, this);
    }
}

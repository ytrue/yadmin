package com.ytrue.yadmin.chat.handle.message;

import com.ytrue.yadmin.chat.constant.SendType;
import com.ytrue.yadmin.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ytrue
 * @date 2021/10/13 14:21
 * @description 群聊消息处理器
 */
@Slf4j
@Component
public class GroupMessageHandle extends AbstractMessageHandle {

    @Override
    public void handle(MessageDTO messageDTO) {
        super.handle(messageDTO);
    }

    @Override
    public void afterPropertiesSet() {
        MessageHandleFactory.register(SendType.GROUP, this);
    }
}

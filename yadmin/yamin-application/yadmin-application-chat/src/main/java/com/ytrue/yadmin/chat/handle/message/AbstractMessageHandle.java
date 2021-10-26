package com.ytrue.yadmin.chat.handle.message;

import com.ytrue.yadmin.dto.MessageDTO;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author ytrue
 * @date 2021/10/14 15:24
 * @description 策略+模板方法设计模式
 */
public abstract class AbstractMessageHandle implements InitializingBean {

    /**
     * 消息处理
     *
     * @param messageDTO
     */
    public void handle(MessageDTO messageDTO) {
        throw new UnsupportedOperationException();
    }

}

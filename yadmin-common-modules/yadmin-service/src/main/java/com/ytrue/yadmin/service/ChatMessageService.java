package com.ytrue.yadmin.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.bean.model.ChatMessage;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/2 11:01
 */
public interface ChatMessageService extends IService<ChatMessage> {
    /**
     * 插入数据，并且更新redis的操作
     *
     * @param message
     * @param ids
     */
    void installMessages(ChatMessage message, List<Integer> ids);
}

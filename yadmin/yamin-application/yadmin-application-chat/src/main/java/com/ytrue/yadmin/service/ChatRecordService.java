package com.ytrue.yadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.dto.MessageDTO;
import com.ytrue.yadmin.model.chat.ChatRecord;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/10/22 15:37
 * @description 聊天记录
 */
public interface ChatRecordService extends IService<ChatRecord> {

    /**
     * 获得聊天记录
     *
     * @param toContactId   接收消息的联系人ID
     * @param fromContactId 消息发送人的信息ID
     * @param currPage      当前页码
     * @param pageSize      每页多少条
     * @return List<MessageDTO>
     */
    List<MessageDTO> getChatRecordList(Long toContactId, Long fromContactId, Long currPage, Long pageSize);

}

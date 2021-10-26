package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.chat.ChatRecordDAO;
import com.ytrue.yadmin.dto.MessageDTO;
import com.ytrue.yadmin.model.chat.ChatRecord;
import com.ytrue.yadmin.service.ChatRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/10/22 15:38
 * @description 聊天记录
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordDAO, ChatRecord> implements ChatRecordService {


    private final ChatRecordDAO chatRecordDAO;


    /**
     * 获得聊天记录
     *
     * @param toContactId   接收消息的联系人ID
     * @param fromContactId 消息发送人的信息ID
     * @param currPage      当前页码
     * @param pageSize      每页多少条
     * @return List<MessageDTO>
     */
    @Override
    public List<MessageDTO> getChatRecordList(Long toContactId, Long fromContactId, Long currPage, Long pageSize) {
        return chatRecordDAO.getChatRecordList(toContactId, fromContactId, currPage, pageSize);
    }
}

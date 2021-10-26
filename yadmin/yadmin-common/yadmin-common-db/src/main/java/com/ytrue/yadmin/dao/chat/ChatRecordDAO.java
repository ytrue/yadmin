package com.ytrue.yadmin.dao.chat;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.dto.MessageDTO;
import com.ytrue.yadmin.model.chat.ChatRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天记录
 */
public interface ChatRecordDAO extends BaseMapper<ChatRecord> {


    /**
     * 获得聊天记录
     *
     * @param toContactId   接收消息的联系人ID
     * @param fromContactId 消息发送人的信息ID
     * @param currPage      当前页码
     * @param pageSize      每页多少条
     * @return MessageDTO
     */
    List<MessageDTO> getChatRecordList(
            @Param("toContactId") Long toContactId,
            @Param("fromContactId") Long fromContactId,
            @Param("currPage") Long currPage,
            @Param("pageSize") Long pageSize
    );
}

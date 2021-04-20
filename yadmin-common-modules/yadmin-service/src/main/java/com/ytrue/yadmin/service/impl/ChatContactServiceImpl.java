package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.bean.dto.MyChatContact;
import com.ytrue.yadmin.bean.model.ChatContact;
import com.ytrue.yadmin.dao.ChatContactMapper;
import com.ytrue.yadmin.service.ChatContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ytrue
 * @date 2021/4/2 11:03
 */
@Service
public class ChatContactServiceImpl extends ServiceImpl<ChatContactMapper, ChatContact> implements ChatContactService {

    @Autowired
    private ChatContactMapper contactMapper;


    @Override
    public MyChatContact getMySidebarChatListByFromId(Integer fromId) {
        return contactMapper.getMySidebarChatListByFromId(fromId);
    }

    @Override
    public MyChatContact getMyChatContactListByFromId(Integer fromId) {
        return contactMapper.getMyChatContactListByFromId(fromId);
    }
}

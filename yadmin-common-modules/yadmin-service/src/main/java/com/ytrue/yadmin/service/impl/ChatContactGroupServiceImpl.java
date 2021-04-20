package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.bean.model.ChatContactGroup;
import com.ytrue.yadmin.dao.ChatContactGroupMapper;
import com.ytrue.yadmin.service.ChatContactGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/9 14:26
 */
@Service
public class ChatContactGroupServiceImpl extends ServiceImpl<ChatContactGroupMapper, ChatContactGroup> implements ChatContactGroupService {

    @Autowired
    private ChatContactGroupMapper chatContactGroupMapper;

    @Override
    public List<Integer> getGroupContactIdsByGroupId(Integer id) {
        return chatContactGroupMapper.getGroupContactIdsByGroupId(id);
    }
}

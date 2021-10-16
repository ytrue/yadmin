package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.dao.chat.ChatContactRelationDAO;
import com.ytrue.yadmin.model.chat.ChatContactRelation;
import com.ytrue.yadmin.service.ChatContactRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 联系人关系表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChatContactRelationServiceImpl extends ServiceImpl<ChatContactRelationDAO, ChatContactRelation> implements ChatContactRelationService {

}

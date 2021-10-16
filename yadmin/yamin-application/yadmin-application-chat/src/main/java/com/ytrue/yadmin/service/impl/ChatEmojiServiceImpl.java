package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.chat.ChatEmojiDAO;
import com.ytrue.yadmin.model.chat.ChatEmoji;
import com.ytrue.yadmin.service.ChatEmojiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天表情库
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChatEmojiServiceImpl extends ServiceImpl<ChatEmojiDAO, ChatEmoji> implements ChatEmojiService {


}

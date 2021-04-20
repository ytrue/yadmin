package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.bean.model.ChatMessage;
import com.ytrue.yadmin.dao.ChatMessageMapper;
import com.ytrue.yadmin.service.ChatMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/2 11:03
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {

    private final static String KEY = "chat-unread";

    @Override
    public void installMessages(ChatMessage message, List<Integer> ids) {

    }

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//
//    @Override
//    @Async("chatMessageSaveExecutor")
//    public void installMessages(ChatMessage message, List<Integer> ids) {
//        save(message);
//        ids.forEach(integer -> stringRedisTemplate.opsForHash().increment(KEY, StringUtil.minmaxValue(integer, message.getToContactId()), 1L));
//    }


}


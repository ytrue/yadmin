package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.chat.ChatEmojiCategoryDAO;
import com.ytrue.yadmin.model.chat.ChatEmojiCategory;
import com.ytrue.yadmin.service.ChatEmojiCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 表情分类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChatEmojiCategoryServiceImpl extends ServiceImpl<ChatEmojiCategoryDAO, ChatEmojiCategory> implements ChatEmojiCategoryService {


}

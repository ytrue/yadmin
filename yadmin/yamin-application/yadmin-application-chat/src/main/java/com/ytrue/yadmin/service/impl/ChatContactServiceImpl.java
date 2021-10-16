package com.ytrue.yadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.chat.ChatContactDAO;
import com.ytrue.yadmin.model.chat.ChatContact;
import com.ytrue.yadmin.service.ChatContactService;
import com.ytrue.yadmin.vo.ContactVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天联系人表
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ChatContactServiceImpl extends ServiceImpl<ChatContactDAO, ChatContact> implements ChatContactService {


    private final ChatContactDAO chatContactDAO;

    /**
     * 根据id获得我的联系人
     *
     * @param contactId
     * @return
     */
    @Override
    public List<ContactVO> getMyContactById(Integer contactId) {
        return chatContactDAO.getMyContactById(contactId);
    }

    /**
     * 获得当前用户侧栏聊天消息列表，限制是前150条
     *
     * @param contactId
     * @return
     */
    @Override
    public List<ContactVO> getMySidebarMessageById(Integer contactId) {
        return chatContactDAO.getMySidebarMessageById(contactId);
    }
}

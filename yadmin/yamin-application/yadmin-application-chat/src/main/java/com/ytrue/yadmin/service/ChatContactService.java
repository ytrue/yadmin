package com.ytrue.yadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.model.chat.ChatContact;
import com.ytrue.yadmin.vo.ContactVO;

import java.util.List;


/**
 * @author ytrue
 * @date 2021/10/15 23:13
 * @description 聊天联系人表
 */
public interface ChatContactService extends IService<ChatContact> {

    /**
     * 根据id获得我的联系人
     *
     * @param contactId
     * @return
     */
    List<ContactVO> getMyContactById(Long contactId);

    /**
     * 获得当前用户侧栏聊天消息列表，限制是前150条
     *
     * @param contactId
     * @return
     */
    List<ContactVO> getMySidebarMessageById(Long contactId);

}
package com.ytrue.yadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.bean.dto.MyChatContact;
import com.ytrue.yadmin.bean.model.ChatContact;

/**
 * @author ytrue
 * @date 2021/4/2 11:01
 */
public interface ChatContactService extends IService<ChatContact> {
    /**
     * 获得侧栏聊天信息，只取前50条
     * @param fromId
     * @return
     */
    MyChatContact getMySidebarChatListByFromId(Integer fromId);

    /**
     * 获得我所以联系人数据
     *
     * @param fromId
     * @return
     */
    MyChatContact getMyChatContactListByFromId(Integer fromId);

}

package com.ytrue.yadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.bean.dto.MyChatContact;
import com.ytrue.yadmin.bean.model.ChatContact;


/**
 * @author ytrue
 * @date 2021/4/2 10:56
 */
public interface ChatContactMapper extends BaseMapper<ChatContact> {

    /**
     * 获得侧栏聊天信息，只取前50条
     *
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

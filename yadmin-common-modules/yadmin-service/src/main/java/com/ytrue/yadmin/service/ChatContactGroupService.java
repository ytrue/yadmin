package com.ytrue.yadmin.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.bean.model.ChatContactGroup;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/9 14:26
 */
public interface ChatContactGroupService extends IService<ChatContactGroup> {
    /**
     * 获得联系人id
     *
     * @param id
     * @return
     */
    List<Integer> getGroupContactIdsByGroupId(Integer id);
}

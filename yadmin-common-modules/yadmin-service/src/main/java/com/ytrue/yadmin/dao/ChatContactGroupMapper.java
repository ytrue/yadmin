package com.ytrue.yadmin.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ytrue.yadmin.bean.model.ChatContactGroup;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/4/9 14:25
 */
public interface ChatContactGroupMapper extends BaseMapper<ChatContactGroup> {

    /**
     * 获得联系人id
     *
     * @param id
     * @return
     */
    List<Integer> getGroupContactIdsByGroupId(Integer id);
}

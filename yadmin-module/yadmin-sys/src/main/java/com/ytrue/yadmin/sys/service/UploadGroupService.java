package com.ytrue.yadmin.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.sys.model.UploadGroup;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库分组记录表
 */

public interface UploadGroupService extends IService<UploadGroup> {


    /**
     * 删除文件分组，并且把改分组的文件group_id变成0
     *
     * @param groupIds
     */
    void deleteGroup(List<Long> groupIds);
}

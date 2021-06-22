package com.ytrue.yadmin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.system.dao.UploadFileDAO;
import com.ytrue.yadmin.system.dao.UploadGroupDAO;
import com.ytrue.yadmin.system.model.UploadFile;
import com.ytrue.yadmin.system.model.UploadGroup;
import com.ytrue.yadmin.system.service.UploadGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库分组记录表
 */

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UploadGroupServiceImpl extends ServiceImpl<UploadGroupDAO, UploadGroup> implements UploadGroupService {


    private final UploadFileDAO uploadFileDAO;


    @Override
    public void deleteGroup(List<Long> groupIds) {
        UploadFile uploadFile = new UploadFile();
        uploadFile.setGroupId(0);
        groupIds.forEach(groupId -> uploadFileDAO.update(uploadFile, new QueryWrapper<UploadFile>().eq("group_id", groupId)));
        //删除文件组
        removeByIds(groupIds);

    }
}

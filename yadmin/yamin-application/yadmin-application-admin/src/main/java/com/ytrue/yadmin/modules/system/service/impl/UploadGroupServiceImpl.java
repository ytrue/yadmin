package com.ytrue.yadmin.modules.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.exeption.YadminException;
import com.ytrue.yadmin.modules.system.dao.UploadFileDAO;
import com.ytrue.yadmin.modules.system.dao.UploadGroupDAO;
import com.ytrue.yadmin.modules.system.model.UploadFile;
import com.ytrue.yadmin.modules.system.model.UploadGroup;
import com.ytrue.yadmin.modules.system.service.UploadGroupService;
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
        groupIds.forEach(groupId -> {
            Assert.isFalse(Convert.toBool(count(new QueryWrapper<UploadGroup>().lambda().eq(UploadGroup::getParentId, groupId))), "请先删除该分类下的数据");
            uploadFileDAO.update(uploadFile, new QueryWrapper<UploadFile>().lambda().eq(UploadFile::getGroupId, groupId));
        });
        //删除文件组
        removeByIds(groupIds);
    }
}

package com.ytrue.yadmin.modules.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ytrue.yadmin.modules.system.dao.SysAttachmentGroupDAO;
import com.ytrue.yadmin.modules.system.dao.SysAttachmentDAO;
import com.ytrue.yadmin.modules.system.model.SysAttachment;
import com.ytrue.yadmin.modules.system.model.SysAttachmentGroup;
import com.ytrue.yadmin.modules.system.service.SysAttachmentGroupService;
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
public class SysAttachmentGroupServiceImpl extends ServiceImpl<SysAttachmentGroupDAO, SysAttachmentGroup> implements SysAttachmentGroupService {


    private final SysAttachmentDAO sysAttachmentDAO;


    @Override
    public void deleteGroup(List<Long> groupIds) {
        SysAttachment sysAttachment = new SysAttachment();
        sysAttachment.setGroupId(0);
        groupIds.forEach(groupId -> {
            Assert.isFalse(Convert.toBool(count(new QueryWrapper<SysAttachmentGroup>().lambda().eq(SysAttachmentGroup::getParentId, groupId))), "请先删除该分类下的数据");
            sysAttachmentDAO.update(sysAttachment, new QueryWrapper<SysAttachment>().lambda().eq(SysAttachment::getGroupId, groupId));
        });
        //删除文件组
        removeByIds(groupIds);
    }
}

package com.ytrue.yadmin.modules.system.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.SettingDao;
import com.ytrue.yadmin.model.Setting;
import com.ytrue.yadmin.modules.system.dao.SysAttachmentDAO;
import com.ytrue.yadmin.modules.system.model.SysAttachment;
import com.ytrue.yadmin.modules.system.service.SysAttachmentService;
import com.ytrue.yadmin.modules.system.service.dto.MoveGroupParamDTO;
import com.ytrue.yadmin.oss.dto.UploadSetting;
import com.ytrue.yadmin.oss.utils.OssUtils;
import com.ytrue.yadmin.utils.FileUtils;
import com.ytrue.yadmin.utils.GsonUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentDAO, SysAttachment> implements SysAttachmentService {

    private final OssUtils ossUtils;

    private final SettingDao settingDao;

    @SneakyThrows
    @Override
    public void uploadFile(MultipartFile file) {
        String extName = FileUtil.extName(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "." + extName;

        //去数据库读取
        UploadSetting uploadSetting = GsonUtils.from(settingDao.selectOne(
                new QueryWrapper<Setting>()
                        .lambda()
                        .eq(Setting::getKey, "storage")).getValues(), UploadSetting.class);

        String uploadPath = ossUtils.upload(uploadSetting, file.getBytes(), fileName);

        //插入数据
        SysAttachment sysAttachment = new SysAttachment();
        //获得文件的名称
        sysAttachment.setFileName(file.getOriginalFilename());
        //文件的路径
        sysAttachment.setFilePath(uploadPath);
        //获得文件大小
        sysAttachment.setFileSize(FileUtils.getPrintSize(file.getSize()));
        //后缀名称
        sysAttachment.setFileExt(extName);
        //插入数据
        save(sysAttachment);
    }

    @Override
    public void moveGroup(MoveGroupParamDTO paramDTO) {
        //获得组
        Integer groupId = paramDTO.getGroupId();
        //这是修改内容
        SysAttachment sysAttachment = new SysAttachment();
        sysAttachment.setGroupId(groupId);
        //循环修改
        paramDTO.getFileIds().forEach(fileId -> {
            update(sysAttachment, new QueryWrapper<SysAttachment>().lambda().eq(SysAttachment::getFileId, fileId));
        });
    }
}

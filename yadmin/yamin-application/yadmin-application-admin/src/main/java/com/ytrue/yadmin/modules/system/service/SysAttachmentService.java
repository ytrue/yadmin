package com.ytrue.yadmin.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.system.model.SysAttachment;
import com.ytrue.yadmin.modules.system.service.dto.MoveGroupParamDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库记录表
 */

public interface SysAttachmentService extends IService<SysAttachment> {

    /**
     * 上传文件
     *
     * @param file
     */
    void uploadFile(MultipartFile file);

    /**
     * 修改图片的组
     *
     * @param paramDTO
     */
    void moveGroup(MoveGroupParamDTO paramDTO);
}

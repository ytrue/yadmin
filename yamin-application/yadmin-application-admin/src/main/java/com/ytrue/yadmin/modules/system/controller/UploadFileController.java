package com.ytrue.yadmin.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.annotation.SysLog;
import com.ytrue.yadmin.annotation.WrapResp;
import com.ytrue.yadmin.modules.system.model.UploadFile;
import com.ytrue.yadmin.search.SearchModel;
import com.ytrue.yadmin.modules.system.service.UploadFileService;
import com.ytrue.yadmin.modules.system.service.dto.MoveGroupParamDTO;
import com.ytrue.yadmin.oss.dto.Engine;
import com.ytrue.yadmin.oss.dto.UploadSetting;
import com.ytrue.yadmin.oss.properties.AliyunProperties;
import com.ytrue.yadmin.oss.properties.LocalProperties;
import com.ytrue.yadmin.oss.properties.QcloudProperties;
import com.ytrue.yadmin.oss.properties.QiniuProperties;
import com.ytrue.yadmin.oss.utils.OssUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/13 18:05
 * @description 文件库记录表
 */
@RestController
@RequestMapping("file")
@WrapResp
@AllArgsConstructor
public class UploadFileController {

    private final UploadFileService uploadFileService;


    /**
     * 列表
     *
     * @param uploadFile 查询的数据
     * @return {@link IPage<UploadFile>}
     */
    @PostMapping("page")
    //@PreAuthorize("@pms.hasPermission('file:page')")
    public IPage<UploadFile> page(@RequestBody SearchModel<UploadFile> uploadFile) {
        return uploadFileService.page(
                uploadFile.getPage(),
                uploadFile.getQueryModel().orderByDesc("file_id")
        );
    }


    private final OssUtils ossUtils;

    /**
     * 上传文件
     *
     * @param file
     */
    @SneakyThrows
    @SysLog("上传文件")
    @PostMapping("upload")
    //@PreAuthorize("@pms.hasPermission('file:upload')")
    public void uploadFiles(@RequestParam("file") MultipartFile file) {
        //uploadFileService.uploadFile(file);


        // ossUtils.upload(uploadSetting,file.getBytes(),"123233432432432.png");

        ossUtils.upload(file.getBytes(), new Date().getTime() + ".png");
    }

    /**
     * 移动图片组
     *
     * @param paramDTO
     */
    @SysLog("移动图片组")
    @PostMapping("move")
    //@PreAuthorize("@pms.hasPermission('file:move')")
    public void moveGroup(@RequestBody MoveGroupParamDTO paramDTO) {
        uploadFileService.moveGroup(paramDTO);
    }

    @SysLog("删除文件")
    @DeleteMapping
    //@PreAuthorize("@pms.hasPermission('file:delete')")
    public void delete(@RequestBody List<Long> fileIds) {
        uploadFileService.removeByIds(fileIds);
    }
}

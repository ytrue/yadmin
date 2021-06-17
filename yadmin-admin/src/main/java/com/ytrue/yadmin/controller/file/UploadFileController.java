package com.ytrue.yadmin.controller.file;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.common.annotation.SysLog;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.search.SearchModel;
import com.ytrue.yadmin.mall.model.UploadFile;
import com.ytrue.yadmin.mall.service.UploadFileService;
import com.ytrue.yadmin.mall.service.dto.MoveGroupParamDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * @param uploadFile
     * @return
     */
    @PostMapping("page")
    //@PreAuthorize("@pms.hasPermission('file:page')")
    public IPage<UploadFile> page(@RequestBody SearchModel<UploadFile> uploadFile) {
        return uploadFileService.page(
                uploadFile.getPage(),
                uploadFile.getQueryModel().orderByDesc("file_id")
        );
    }

    /**
     * 上传文件
     *
     * @param file
     */
    @SysLog("上传文件")
    @PostMapping("upload")
    //@PreAuthorize("@pms.hasPermission('file:upload')")
    public void uploadFiles(@RequestParam("file") MultipartFile file) {
        uploadFileService.uploadFile(file);
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

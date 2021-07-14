package com.ytrue.yadmin.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.modules.system.model.UploadFile;
import com.ytrue.yadmin.modules.system.service.UploadFileService;
import com.ytrue.yadmin.modules.system.service.dto.MoveGroupParamDTO;
import com.ytrue.yadmin.oss.utils.OssUtils;
import com.ytrue.yadmin.search.SearchModel;
import com.ytrue.yadmin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@AllArgsConstructor
@Api(tags = "文件库记录")
public class UploadFileController {

    private final UploadFileService uploadFileService;


    /**
     * 列表
     *
     * @param uploadFile 查询的数据
     * @return {@link IPage<UploadFile>}
     */
    @PostMapping("page")
    @ApiOperation(value = "分页查询数据")
    //@PreAuthorize("@pms.hasPermission('file:page')")
    public R<IPage<UploadFile>> page(@RequestBody SearchModel<UploadFile> uploadFile) {
        IPage<UploadFile> page = uploadFileService.page(
                uploadFile.getPage(),
                uploadFile.getQueryModel().lambda().orderByDesc(UploadFile::getFileId)
        );
        return R.success(page);
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
    @ApiOperation("上传文件")
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
    @ApiOperation("移动图片组")
    //@PreAuthorize("@pms.hasPermission('file:move')")
    public void moveGroup(@RequestBody MoveGroupParamDTO paramDTO) {
        uploadFileService.moveGroup(paramDTO);
    }

    @SysLog("删除文件")
    @DeleteMapping
    @ApiOperation(value = "删除文件")
    //@PreAuthorize("@pms.hasPermission('file:delete')")
    public void delete(
            @ApiParam(required = true, name = "id集合")
            @RequestBody List<Long> fileIds
    ) {
        uploadFileService.removeByIds(fileIds);
    }
}

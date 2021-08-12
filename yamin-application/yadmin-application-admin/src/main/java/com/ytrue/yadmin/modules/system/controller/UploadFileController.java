package com.ytrue.yadmin.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.modules.system.model.UploadFile;
import com.ytrue.yadmin.modules.system.service.UploadFileService;
import com.ytrue.yadmin.modules.system.service.dto.MoveGroupParamDTO;
import com.ytrue.yadmin.search.SearchModel;
import com.ytrue.yadmin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ytrue
 * @date 2021/6/13 18:05
 * @description 文件库记录表
 */
@RestController
@RequestMapping("files/file")
@AllArgsConstructor
@Api(tags = "文件管理")
public class UploadFileController {

    private final UploadFileService uploadFileService;


    @PostMapping("page")
    @ApiOperation("分页查询数据")
    //@PreAuthorize("@pms.hasPermission('file:page')")
    public R<IPage<UploadFile>> page(@RequestBody SearchModel<UploadFile> uploadFile) {
        IPage<UploadFile> page = uploadFileService.page(
                uploadFile.getPage(),
                uploadFile.getQueryModel().lambda().orderByDesc(UploadFile::getFileId)
        );
        return R.success(page);
    }


    @SysLog
    @SneakyThrows
    @PostMapping("upload")
    @ApiOperation("上传文件")
    //@PreAuthorize("@pms.hasPermission('file:upload')")
    public void uploadFiles(@RequestParam("file") MultipartFile file) {
        uploadFileService.uploadFile(file);
    }


    @ApiOperation("文件信息")
    @GetMapping("{fileId}/info")
    public UploadFile info(@PathVariable("fileId") Long fileId) {
        return uploadFileService.getById(fileId);
    }

    @SysLog
    @PutMapping
    @ApiOperation("修改文件")
    public void update(@Validated @RequestBody UploadFile uploadFile) {
        uploadFileService.updateById(uploadFile);
    }

    @SysLog
    @PostMapping("move")
    @ApiOperation("移动文件组")
    //@PreAuthorize("@pms.hasPermission('file:move')")
    public void moveGroup(@RequestBody MoveGroupParamDTO paramDTO) {
        uploadFileService.moveGroup(paramDTO);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除文件")
    //@PreAuthorize("@pms.hasPermission('file:delete')")
    public void delete(
            @ApiParam(required = true, name = "id集合")
            @RequestBody List<Long> fileIds
    ) {
        uploadFileService.removeByIds(fileIds);
    }
}

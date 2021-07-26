package com.ytrue.yadmin.modules.system.controller;

import com.ytrue.yadmin.exeption.YadminException;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.modules.system.service.UploadGroupService;
import com.ytrue.yadmin.modules.system.model.UploadGroup;
import com.ytrue.yadmin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ytrue
 * @date 2021/6/13 18:05
 * @description 文件库分组记录表
 */
@RestController
@RequestMapping("files/group")
@AllArgsConstructor
@Api(tags = "文件分组")
public class UploadGroupController {

    private final UploadGroupService uploadGroupService;


    @GetMapping("list")
    @ApiOperation("查询所有文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:list')")
    public List<UploadGroup> list() {
        return uploadGroupService.list();
    }


    @SysLog("保存文件分组")
    @PostMapping
    @ApiOperation("保存文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:save')")
    public void save(@Validated @RequestBody UploadGroup uploadGroup) {
        uploadGroupService.save(uploadGroup);
    }


    @PutMapping
    @SysLog("修改文件分组")
    @ApiOperation("修改文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:update')")
    public void update(@Validated @RequestBody UploadGroup uploadGroup) {
        if (uploadGroup.getGroupId().equals(uploadGroup.getParentId())) {
            throw new YadminException("自己不能是自己的上级");
        }
        uploadGroupService.updateById(uploadGroup);
    }


    @GetMapping("{groupId}")
    @ApiOperation("文件分组信息")
    public UploadGroup info(@PathVariable("groupId") Long groupId) {
        return uploadGroupService.getById(groupId);
    }

    @DeleteMapping
    @SysLog("删除文件分组")
    @ApiOperation("删除文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:delete')")
    public void delete(@ApiParam(required = true, name = "id集合") @RequestBody List<Long> groupIds) {
        uploadGroupService.deleteGroup(groupIds);
    }

}

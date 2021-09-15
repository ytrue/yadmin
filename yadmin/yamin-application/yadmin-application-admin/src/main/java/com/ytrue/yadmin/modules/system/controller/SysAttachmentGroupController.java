package com.ytrue.yadmin.modules.system.controller;

import com.ytrue.yadmin.enums.StrPool;
import com.ytrue.yadmin.exeption.YadminException;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.modules.system.model.SysAttachmentGroup;
import com.ytrue.yadmin.modules.system.service.SysAttachmentGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("sys/attachmentGroup")
@AllArgsConstructor
@Api(tags = "文件分组")
public class SysAttachmentGroupController {

    private final SysAttachmentGroupService sysAttachmentGroupService;


    @GetMapping("list")
    @ApiOperation("查询所有文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:list')")
    public List<SysAttachmentGroup> list() {
        return sysAttachmentGroupService.list();
    }


    @SysLog
    @PostMapping
    @ApiOperation("保存文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:save')")
    public void save(@Validated @RequestBody SysAttachmentGroup uploadGroup) {
        sysAttachmentGroupService.save(uploadGroup);
    }

    @SysLog
    @PutMapping
    @ApiOperation("修改文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:update')")
    public void update(@Validated @RequestBody SysAttachmentGroup uploadGroup) {
        if (uploadGroup.getGroupId().equals(uploadGroup.getParentId())) {
            throw new YadminException(StrPool.YOU_CANT_BE_YOUR_SUPERIOR.getMessage());
        }
        sysAttachmentGroupService.updateById(uploadGroup);
    }


    @GetMapping("{groupId}")
    @ApiOperation("文件分组信息")
    public SysAttachmentGroup info(@PathVariable("groupId") Long groupId) {
        return sysAttachmentGroupService.getById(groupId);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除文件分组")
    //@PreAuthorize("@pms.hasPermission('file:group:delete')")
    public void delete(@ApiParam(required = true, name = "id集合") @RequestBody List<Long> groupIds) {
        sysAttachmentGroupService.deleteGroup(groupIds);
    }

}

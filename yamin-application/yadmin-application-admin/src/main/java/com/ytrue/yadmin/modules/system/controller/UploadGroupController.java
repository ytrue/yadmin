package com.ytrue.yadmin.modules.system.controller;

import com.ytrue.yadmin.exeption.YadminException;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.modules.system.service.UploadGroupService;
import com.ytrue.yadmin.modules.system.model.UploadGroup;
import com.ytrue.yadmin.utils.R;
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
@RequestMapping("file.group")
@AllArgsConstructor
@Api(tags = "文件库分组记录")
public class UploadGroupController {

    private final UploadGroupService uploadGroupService;

    /**
     * 列表
     *
     * @return {@link List<UploadGroup>}
     */
    @PostMapping("list")
    @ApiOperation(value = "查询所有件库分组")
    //@PreAuthorize("@pms.hasPermission('file:group:list')")
    public R<List<UploadGroup>> list() {
        return R.success(uploadGroupService.list());
    }


    /**
     * 保存文件分组
     *
     * @param uploadGroup
     */
    @SysLog("保存文件分组")
    @PostMapping
    @ApiOperation(value = "保存文件分组", response = R.class)
    //@PreAuthorize("@pms.hasPermission('file:group:save')")
    public void save(@Validated @RequestBody UploadGroup uploadGroup) {
        uploadGroupService.save(uploadGroup);
    }


    /**
     * 修改文件分组
     *
     * @param uploadGroup
     */
    @SysLog("修改文件分组")
    @PutMapping
    @ApiOperation(value = "修改文件分组", response = R.class)
    //@PreAuthorize("@pms.hasPermission('file:group:update')")
    public void update(@Validated @RequestBody UploadGroup uploadGroup) {
        if (uploadGroup.getGroupId().equals(uploadGroup.getParentId())) {
            throw new YadminException("自己不能是自己的上级");
        }
        uploadGroupService.updateById(uploadGroup);
    }


    @GetMapping("{groupId}")
    public UploadGroup info(@PathVariable("groupId") Long groupId) {
        return uploadGroupService.getById(groupId);
    }

    /**
     * 删除文件分组
     *
     * @param groupIds
     */
    @SysLog("删除文件分组")
    @DeleteMapping
    @ApiOperation(value = "删除文件分组", response = R.class)
    //@PreAuthorize("@pms.hasPermission('file:group:delete')")
    public void delete(@ApiParam(required = true, name = "id集合") @RequestBody List<Long> groupIds) {
        uploadGroupService.deleteGroup(groupIds);
    }

}

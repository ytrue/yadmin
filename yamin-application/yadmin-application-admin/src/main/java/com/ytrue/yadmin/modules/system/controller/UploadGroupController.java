package com.ytrue.yadmin.modules.system.controller;

import com.ytrue.yadmin.annotation.SysLog;
import com.ytrue.yadmin.annotation.WrapResp;
import com.ytrue.yadmin.modules.system.service.UploadGroupService;
import com.ytrue.yadmin.modules.system.model.UploadGroup;
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
@WrapResp
@AllArgsConstructor
public class UploadGroupController {

    private final UploadGroupService uploadGroupService;

    /**
     * 列表
     *
     * @return {@link List<UploadGroup>}
     */
    @PostMapping("list")
    //@PreAuthorize("@pms.hasPermission('file:group:list')")
    public List<UploadGroup> page() {
        return uploadGroupService.list();
    }


    /**
     * 保存文件分组
     *
     * @param uploadGroup
     */
    @SysLog("保存文件分组")
    @PostMapping
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
    //@PreAuthorize("@pms.hasPermission('file:group:update')")
    public void update(@Validated @RequestBody UploadGroup uploadGroup) {
        uploadGroupService.updateById(uploadGroup);
    }

    /**
     * 删除文件分组
     *
     * @param groupIds
     */
    @SysLog("删除文件分组")
    @DeleteMapping
    //@PreAuthorize("@pms.hasPermission('file:group:delete')")
    public void delete(@RequestBody List<Long> groupIds) {
        uploadGroupService.deleteGroup(groupIds);
    }

}

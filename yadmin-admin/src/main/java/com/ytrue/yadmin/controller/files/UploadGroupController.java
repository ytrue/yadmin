package com.ytrue.yadmin.controller.files;

import com.ytrue.yadmin.common.annotation.AutoValid;
import com.ytrue.yadmin.common.annotation.SysLog;
import com.ytrue.yadmin.common.annotation.WrapResp;

import com.ytrue.yadmin.system.model.UploadGroup;
import com.ytrue.yadmin.system.service.UploadGroupService;
import lombok.AllArgsConstructor;
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
     * @return
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
    @AutoValid(entity = UploadGroup.class)
    //@PreAuthorize("@pms.hasPermission('file:group:save')")
    public void save(@RequestBody UploadGroup uploadGroup) {
        uploadGroupService.save(uploadGroup);
    }


    /**
     * 修改文件分组
     *
     * @param uploadGroup
     */
    @SysLog("修改文件分组")
    @PutMapping
    @AutoValid(entity = UploadGroup.class)
    //@PreAuthorize("@pms.hasPermission('file:group:update')")
    public void update(@RequestBody UploadGroup uploadGroup) {
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

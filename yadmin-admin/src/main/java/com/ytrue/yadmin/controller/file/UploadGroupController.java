package com.ytrue.yadmin.controller.file;

import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.mall.model.UploadGroup;
import com.ytrue.yadmin.mall.service.UploadGroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<UploadGroup> page() {
        return uploadGroupService.list();
    }

}

package com.ytrue.yadmin.controller.file;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.common.annotation.WrapResp;
import com.ytrue.yadmin.common.search.SearchModel;
import com.ytrue.yadmin.mall.model.UploadFile;
import com.ytrue.yadmin.mall.service.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public IPage<UploadFile> page(@RequestBody SearchModel<UploadFile> uploadFile) {
        return uploadFileService.page(
                uploadFile.getPage(),
                uploadFile.getQueryModel().orderByDesc("file_id")
        );
    }

}

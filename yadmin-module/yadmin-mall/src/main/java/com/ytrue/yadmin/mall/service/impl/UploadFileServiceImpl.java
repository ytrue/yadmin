package com.ytrue.yadmin.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.mall.dao.UploadFileDAO;
import com.ytrue.yadmin.mall.model.UploadFile;
import com.ytrue.yadmin.mall.service.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UploadFileServiceImpl extends ServiceImpl<UploadFileDAO, UploadFile> implements UploadFileService {


    private final UploadFileDAO uploadFileDAO;


}

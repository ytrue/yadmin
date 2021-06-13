package com.ytrue.yadmin.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.mall.dao.UploadGroupDAO;
import com.ytrue.yadmin.mall.model.UploadGroup;
import com.ytrue.yadmin.mall.service.UploadGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/6/13 17:59
 * @description 文件库分组记录表
 */

@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class UploadGroupServiceImpl extends ServiceImpl<UploadGroupDAO, UploadGroup> implements UploadGroupService {


    private final UploadGroupDAO uploadGroupDAO;


}

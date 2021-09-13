package com.ytrue.yadmin.modules.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.dao.mall.setting.ExpressDAO;
import com.ytrue.yadmin.model.mall.setting.Express;
import com.ytrue.yadmin.modules.mall.service.ExpressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ytrue
 * @date 2021/9/13 17:22
 * @description 物流公司记录表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExpressServiceImpl extends ServiceImpl<ExpressDAO, Express> implements ExpressService {

}

package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.model.SysRole;
import com.ytrue.yadmin.modules.system.dao.SysRoleDao;
import com.ytrue.yadmin.modules.system.service.SysRoleService;

import org.springframework.stereotype.Service;
import java.util.Objects;

/**
* @author ytrue
* @date 2022-06-29
* @description 角色Service实现类
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Override
    public IPage<SysRole> paginate(QueryEntity<SysRole> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }
}

package com.ytrue.yadmin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.tools.query.entity.QueryEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ytrue.yadmin.modules.system.model.SysUserRole;
import com.ytrue.yadmin.modules.system.dao.SysUserRoleDao;
import com.ytrue.yadmin.modules.system.service.SysUserRoleService;

import org.springframework.stereotype.Service;
import java.util.Objects;

/**
* @author ytrue
* @date 2022-06-29
* @description 用户与角色对应关系Service实现类
*/
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Override
    public IPage<SysUserRole> paginate(QueryEntity<SysUserRole> queryEntity) {
        queryEntity = Objects.isNull(queryEntity) ? new QueryEntity<>() : queryEntity;
        return page(queryEntity.getPage(), queryEntity.getQueryModel());
    }
}

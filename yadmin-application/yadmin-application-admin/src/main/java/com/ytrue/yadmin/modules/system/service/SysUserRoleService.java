package com.ytrue.yadmin.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.system.model.SysUserRole;

import java.util.List;

/**
* @author ytrue
* @date 2022-06-29
* @description 用户与角色对应关系Service
*/
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
    * 分页查询
    * @param queryEntity
    * @return
    */
    IPage<SysUserRole> paginate(QueryEntity<SysUserRole> queryEntity);
}

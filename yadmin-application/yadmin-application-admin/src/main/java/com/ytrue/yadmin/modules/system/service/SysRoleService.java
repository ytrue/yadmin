package com.ytrue.yadmin.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ytrue.yadmin.modules.system.model.SysRole;

import java.util.List;

/**
* @author ytrue
* @date 2022-06-29
* @description 角色Service
*/
public interface SysRoleService extends IService<SysRole> {

    /**
    * 分页查询
    * @param queryEntity
    * @return
    */
    IPage<SysRole> paginate(QueryEntity<SysRole> queryEntity);
}
